package voot.provider;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrouperSoapClient extends AbstractProvider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  private final NamespaceContext grouperNameSpaceContext = new GrouperNameSpaceContext();

  private final Pattern replacementPattern = Pattern.compile("\\[(.+?)\\]");

  private final Charset charSet = Charset.forName("UTF-8");

  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  private final Map<String, String> soapTemplates = new HashMap<>();

  public GrouperSoapClient(Configuration configuration) {
    super(configuration);
    this.factory.setNamespaceAware(true);
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return true;
  }

  @Override
  public boolean shouldBeQueriedForGroup(String schacHomeOrganization, String groupId) {
    Matcher matcher = groupPattern.matcher(groupId);
    /*
     * For unqualified group names we do NOT query Grouper. This is a design decision discussed with SURFnet
     */
    return matcher.matches() && matcher.group(1).equals(configuration.schacHomeOrganization);
  }

  @Override
  public List<Group> getGroupMemberships(final String uid) {
    Map<String, String> replacements = ImmutableMap.of("subjectId", uid);

    try {
      LOG.debug("Querying getGroupMemberships for subjectId: {}", uid);
      String soap = replaceTokens("soap/GetGroupsLite.xml", replacements);

      ResponseEntity<String> response = getGrouperResponse(soap);

      List<Group> groups = parseGroups(response);

      LOG.debug("getGroupMemberships result: {} groups.", groups.size());
      return groups;

    } catch (Exception exception) {
      LOG.warn("Failed to invoke getGroupMemberships, returning empty result.", exception);
      return Collections.emptyList();
    }
  }

  @Override
  public Optional<Group> getGroupMembership(String uid, String groupId) {
    groupId = stripGroupUrnIdentifier(groupId);
    Map<String, String> replacements = ImmutableMap.of("subjectId", uid, "groupId", groupId);
    try {
      LOG.debug("Querying getGroupMembership API for subjectId: {}", uid);
      String soap = replaceTokens("soap/HasMemberLite.xml", replacements);

      ResponseEntity<String> response = getGrouperResponse(soap);

      Optional<Group> group = parseOptionalGroupMembership(response);
      LOG.debug("getGroupMembership result: {} group.", group);
      return group;
    } catch (Exception exception) {
      LOG.warn("Failed to invoke grouper, returning empty result.", exception);
      return Optional.empty();
    }

  }

  private ResponseEntity<String> getGrouperResponse(String soap) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_XML);
    HttpEntity<String> entity = new HttpEntity<>(soap, headers);

    return restTemplate.exchange(configuration.url, HttpMethod.POST, entity, String.class);
  }

  private List<Group> parseGroups(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    LOG.debug("result from Grouper: {} .", response);

    Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(response.getBody().getBytes()));

    XPath xpath = XPathFactory.newInstance().newXPath();
    xpath.setNamespaceContext(grouperNameSpaceContext);

    NodeList nodes = (NodeList) xpath.evaluate("//ns:wsGroups", document, XPathConstants.NODESET);
    List<Group> groups = new ArrayList<>();

    for (int i = 0; i < nodes.getLength(); i++) {
      Node item = nodes.item(i);
      if (nonNilNode(item)) {
        groups.add(parseGroup(xpath, item));
      }
    }
    return groups;
  }

  private boolean nonNilNode(Node node) {
    Node attribute = node.getAttributes().getNamedItem("xsi:nil");
    return attribute == null || !Boolean.valueOf(attribute.getNodeValue());
  }

  private Group parseGroup(XPath xpath, Node item) throws XPathExpressionException {
    return new Group(
      groupIdPrefix + xpath.evaluate("ns:name", item),
      xpath.evaluate("ns:displayExtension", item),
      xpath.evaluate("ns:description", item),
      configuration.name,
      //Grouper does not hand out the membership, but for consistency with the other VOOT providers we default to
      Membership.defaultMembership);
  }

  private Optional<Group> parseOptionalGroupMembership(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(response.getBody().getBytes()));

    XPath xpath = XPathFactory.newInstance().newXPath();
    xpath.setNamespaceContext(grouperNameSpaceContext);

    String resultCode = (String) xpath.evaluate("//ns:resultCode[1]", document, XPathConstants.STRING);
    if (resultCode.equals("IS_MEMBER")) {
      Node group = (Node) xpath.evaluate("//ns:wsGroup[1]", document, XPathConstants.NODE);
      return Optional.of(parseGroup(xpath, group));
    } else {
      return Optional.empty();
    }
  }

  private String replaceTokens(String soapTemplate, Map<String, String> replacements) throws IOException {
    String xml = soapTemplates.get(soapTemplate);
    if (xml == null) {
      xml = StreamUtils.copyToString(new ClassPathResource(soapTemplate).getInputStream(), charSet);
      soapTemplates.put(soapTemplate, xml);
    }
    Matcher matcher = replacementPattern.matcher(xml);
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      String replacement = replacements.get(matcher.group(1));
      if (replacement != null) {
        matcher.appendReplacement(buffer, "");
        buffer.append(replacement);
      }
    }
    matcher.appendTail(buffer);
    String result = buffer.toString();
    return result;

  }
}
