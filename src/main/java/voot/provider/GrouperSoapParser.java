package voot.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import voot.valueobject.Group;
import voot.valueobject.Member;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrouperSoapParser {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapParser.class);

  private final NamespaceContext grouperNameSpaceContext = new GrouperNameSpaceContext();

  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  private final String sourceId;
  private final String groupIdPrefix;

  public GrouperSoapParser(String sourceId, String groupIdPrefix) {
    this.factory.setNamespaceAware(true);
    this.sourceId = sourceId;
    this.groupIdPrefix = groupIdPrefix;
  }

  public List<Group> parseGroups(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    return doGetGroups(response, "//ns:wsGroups");
  }

  public List<Group> parseFindAllGroups(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    return doGetGroups(response, "//ns:groupResults");
  }

  private List<Group> doGetGroups(ResponseEntity<String> response, String groupElement) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    LOG.debug("result from Grouper: {} .", response);

    Document document = getDocument(response);
    XPath xpath = getXPath();

    NodeList nodes = (NodeList) xpath.evaluate(groupElement, document, XPathConstants.NODESET);
    List<Group> groups = new ArrayList<>();

    for (int i = 0; i < nodes.getLength(); i++) {
      Node item = nodes.item(i);
      if (nonNilNode(item)) {
        groups.add(parseGroup(xpath, item));
      }
    }
    return groups;
  }

  public List<Member> parseMembers(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    LOG.debug("result from Grouper: {} .", response);

    Document document = getDocument(response);
    XPath xpath = getXPath();

    NodeList nodes = (NodeList) xpath.evaluate("//ns:wsSubjects", document, XPathConstants.NODESET);
    List<Member> groups = new ArrayList<>();

    for (int i = 0; i < nodes.getLength(); i++) {
      Node item = nodes.item(i);
      if (nonNilNode(item)) {
        groups.add(parseMember(xpath, item));
      }
    }
    return groups;
  }

  public Optional<Group> parseOptionalGroupMembership(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
    Document document = getDocument(response);

    XPath xpath = getXPath();

    String resultCode = (String) xpath.evaluate("//ns:resultCode[1]", document, XPathConstants.STRING);
    if (resultCode.equals("IS_MEMBER")) {
      Node group = (Node) xpath.evaluate("//ns:wsGroup[1]", document, XPathConstants.NODE);
      return Optional.of(parseGroup(xpath, group));
    } else {
      return Optional.empty();
    }
  }

  public List<String> parsePrivileges(ResponseEntity<String> response, String uid) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    Document document = getDocument(response);
    XPath xpath = getXPath();

    NodeList nodes = (NodeList) xpath.evaluate("//ns:privilegeResults", document, XPathConstants.NODESET);
    List<String> privileges = new ArrayList<>();

    for (int i = 0; i < nodes.getLength(); i++) {
      Node item = nodes.item(i);
      if (nonNilNode(item)) {
        Node subject = (Node) xpath.evaluate("ns:wsSubject", item, XPathConstants.NODE);
        if (nonNilNode(subject)) {
          String id = xpath.evaluate("ns:id", subject);
          if (id != null && id.equals(uid)) {
            privileges.add(xpath.evaluate("ns:privilegeName", item).toLowerCase());
          }
        }
      }
    }
    return privileges;
  }

  private Member parseMember(XPath xpath, Node item) throws XPathExpressionException {
    return new Member(
      xpath.evaluate("ns:id", item),
      xpath.evaluate("ns:name", item),
      xpath.evaluate("ns:attributeValues", item)
    );
  }

  private Group parseGroup(XPath xpath, Node item) throws XPathExpressionException {
    return new Group(
      groupIdPrefix + xpath.evaluate("ns:name", item),
      xpath.evaluate("ns:displayExtension", item),
      xpath.evaluate("ns:description", item),
      sourceId,
      //Grouper does not hand out the membership, but for consistency with the other VOOT providers we default to
      Membership.MEMBER);
  }

  private XPath getXPath() {
    XPath xpath = XPathFactory.newInstance().newXPath();
    xpath.setNamespaceContext(grouperNameSpaceContext);
    return xpath;
  }

  private boolean nonNilNode(Node node) {
    Node attribute = node.getAttributes().getNamedItem("xsi:nil");
    return attribute == null || !Boolean.valueOf(attribute.getNodeValue());
  }

  private Document getDocument(ResponseEntity<String> response) throws SAXException, IOException, ParserConfigurationException {
    return factory.newDocumentBuilder().parse(new ByteArrayInputStream(response.getBody().getBytes()));
  }

}
