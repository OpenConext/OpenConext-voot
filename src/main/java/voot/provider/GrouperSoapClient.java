package voot.provider;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import voot.util.UrnUtils;
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
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrouperSoapClient extends AbstractProvider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  public static final String URN_GET_GROUPS_LITE = "urn:getGroupsLite";
  public static final String URN_GET_MEMBERS_LITE = "urn:getMembersLite";
  public static final String URN_GET_GROUPER_PRIVILEGES_LITE = "urn:getGrouperPrivilegesLite";
  public static final String URN_HAS_MEMBER_LITE = "urn:hasMemberLite";

  public static final String SOAP_ACTION = "SOAPAction";

  private final Pattern replacementPattern = Pattern.compile("\\[(.+?)\\]");

  private final Charset charSet = Charset.forName("UTF-8");

  private final Map<String, String> soapTemplates = new HashMap<>();

  private final GrouperSoapParser soapParser;

  private final ForkJoinPool forkJoinPool;

  public GrouperSoapClient(Configuration configuration) {
    super(configuration);
    soapParser = new GrouperSoapParser(configuration.name, groupIdPrefix);
    this.forkJoinPool = new ForkJoinPool(16);
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return true;
  }

  @Override
  public List<Group> getGroupMemberships(final String uid, boolean includeMemberships) {
    Map<String, String> replacements = new HashMap<>();
    replacements.put("subjectId", uid);

    try {
      LOG.debug("Querying getGroupMemberships for subjectId: {}", uid);
      String soap = replaceTokens("soap/GetGroupsLite.xml", replacements);

      ResponseEntity<String> response = getGrouperResponse(soap, URN_GET_GROUPS_LITE);
      List<Group> groups = soapParser.parseGroups(response);

      if (includeMemberships) {
        return forkJoinPool.submit(() ->
          groups.parallelStream()
            .map(group -> correctMembership(group, uid))
            .collect(Collectors.toList())).get();

      }
      return groups;

    } catch (Exception exception) {
      LOG.warn("Failed to invoke getGroupMemberships, returning empty result.", exception);
      return Collections.emptyList();
    }
  }

  @Override
  public Optional<Group> getGroupMembership(final String uid, final String groupId) {
    final Optional<String> localGroupId = getLocalGroupId(groupId);
    Map<String, String> replacements = new HashMap<>();
    replacements.put("subjectId", uid);
    replacements.put("groupId", localGroupId.get());

    try {
      LOG.debug("Querying getGroupMembership API for subjectId: {}", uid);
      String soap = replaceTokens("soap/HasMemberLite.xml", replacements);

      ResponseEntity<String> response = getGrouperResponse(soap, URN_HAS_MEMBER_LITE);
      Optional<Group> group = soapParser.parseOptionalGroupMembership(response);

      LOG.debug("getGroupMembership result: {} group.", group);
      return group;
    } catch (Exception exception) {
      LOG.warn("Failed to invoke grouper, returning empty result.", exception);
      return Optional.empty();
    }
  }

  @Override
  public List<Member> getMembers(String groupId) {
    final Optional<String> localGroupId = getLocalGroupId(groupId);
    Map<String, String> replacements = new HashMap<>();
    replacements.put("groupId", localGroupId.get());

    try {
      LOG.debug("Querying getMembers API for groupId: {}", localGroupId.get());
      String soap = replaceTokens("soap/GetMembersLite.xml", replacements);

      ResponseEntity<String> response = getGrouperResponse(soap, URN_GET_MEMBERS_LITE);
      List<Member> members = soapParser.parseMembers(response);

      LOG.debug("getMembers result: {} .", members);
      return members;
    } catch (Exception exception) {
      LOG.warn("Failed to invoke grouper, returning empty result.", exception);
      return Collections.emptyList();
    }
  }

  protected Group correctMembership(Group group, String uid) {
    Map<String, String> replacements = new HashMap<>();
    //we need to strip the SURFnet collab prefix
    String groupIdWithoutPrefix = StringEscapeUtils.escapeXml11(group.id.replace(groupIdPrefix, ""));
    replacements.put("groupId", groupIdWithoutPrefix);

    LOG.debug("Querying GetPrivileges for group: {} from Thread {}", groupIdWithoutPrefix, Thread.currentThread().getName());
    try {
      String soap = replaceTokens("soap/GetPrivilegesLite.xml", replacements);

      ResponseEntity<String> response = getGrouperResponse(soap, URN_GET_GROUPER_PRIVILEGES_LITE);
      List<String> memberships = soapParser.parsePrivileges(response, uid);
      Group newGroup;
      if (memberships.contains("admin")) {
        newGroup = new Group(group, Membership.fromRole("admin"));
      } else if (memberships.contains("update")) {
        newGroup = new Group(group, Membership.fromRole("manager"));
      } else {
        newGroup = new Group(group, Membership.defaultMembership);
      }
      LOG.debug("GetPrivileges result: {} for Group {} and uid {}", newGroup.membership, groupIdWithoutPrefix, uid);
      return newGroup;
    } catch (Exception exception) {
      if (exception instanceof HttpServerErrorException) {
        HttpServerErrorException serverError = (HttpServerErrorException) exception;
        LOG.warn("Failed to invoke grouper, not correcting membership. Cause: " + serverError.getResponseBodyAsString());
      } else {
        LOG.warn("Failed to invoke grouper, not correcting membership.", exception);
      }
      return group;
    }
  }

  private ResponseEntity<String> getGrouperResponse(String soap, String action) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_XML);
    headers.set(SOAP_ACTION, action);

    HttpEntity<String> entity = new HttpEntity<>(soap, headers);

    return restTemplate.exchange(configuration.url, HttpMethod.POST, entity, String.class);
  }

  private Optional<String> getLocalGroupId(String groupId) {
    final Optional<String> localGroupId = UrnUtils.extractLocalGroupId(groupId);
    if (!localGroupId.isPresent()) {
      throw new IllegalArgumentException("Unable to infer localgroupId from " + groupId);
    }
    return localGroupId;
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
    return buffer.toString();

  }
}
