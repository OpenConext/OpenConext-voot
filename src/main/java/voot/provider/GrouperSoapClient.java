package voot.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StreamUtils;
import voot.util.UrnUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static voot.util.UrnUtils.extractLocalGroupId;

public class GrouperSoapClient extends AbstractProvider implements GrouperProvider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  public static final String URN_GET_MEMBERS_LITE = "urn:getMembersLite";
  public static final String URN_HAS_MEMBER_LITE = "urn:hasMemberLite";
  public static final String URN_FIND_GROUPS_LITE = "urn:findGroupsLite";

  public static final String SOAP_ACTION = "SOAPAction";

  private final Pattern replacementPattern = Pattern.compile("\\[(.+?)\\]");

  private final Charset charSet = Charset.forName("UTF-8");

  private final Map<String, String> soapTemplates = new HashMap<>();

  private final GrouperSoapParser soapParser;

  private final GrouperDao grouperDao;

  public GrouperSoapClient(Configuration configuration, DataSource grouperDataSource) {
    super(configuration);
    this.grouperDao = new GrouperDaoClient(new NamedParameterJdbcTemplate(grouperDataSource), configuration.name, groupIdPrefix);
    this.soapParser = new GrouperSoapParser(configuration.name, groupIdPrefix);
  }

  public GrouperSoapClient(Configuration configuration, GrouperDao grouperDao) {
    super(configuration);
    this.grouperDao = grouperDao;
    this.soapParser = new GrouperSoapParser(configuration.name, groupIdPrefix);
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return true;
  }

  @Override
  public List<Group> getGroupMemberships(final String uid) {
    LOG.debug("Querying database getGroupMemberships for subjectId: {}", uid);
    try {
      return grouperDao.groups(uid);
    } catch (Exception exception) {
      LOG.warn("Failed to invoke getGroupMemberships, returning empty result.", exception);
      return Collections.emptyList();
    }
  }

  @Override
  public Optional<Group> getGroupMembership(final String uid, final String groupId) {
    String localGroupId = extractLocalGroupId(groupId);
    Map<String, String> replacements = new HashMap<>();
    replacements.put("subjectId", uid);
    replacements.put("groupId", localGroupId);

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

  public List<Group> getGroupMembershipsForLocalGroupId(final String... localGroupId) {
    return grouperDao.groupsByName(localGroupId);
  }

  public boolean isGrouperGroup(String groupId) {
    Matcher matcher = UrnUtils.GROUP_PATTERN.matcher(groupId);
    return matcher.matches() && matcher.group(1).equals(configuration.schacHomeOrganization);
  }

  @Override
  public List<Group> getAllGroups() {
    try {
      LOG.debug("Querying findGroupsLite API");
      String soap = getTemplate("soap/FindGroupsLite.xml");

      ResponseEntity<String> response = getGrouperResponse(soap, URN_FIND_GROUPS_LITE);
      List<Group> groups = soapParser.parseFindAllGroups(response);

      LOG.debug("getGroupMembership result: {} group.", groups.size());
      return groups;
    } catch (Exception exception) {
      LOG.warn("Failed to invoke grouper, returning empty result.", exception);
      return Collections.emptyList();
    }
  }

  @Override
  public List<Member> getMembers(String groupId) {
    String localGroupId = extractLocalGroupId(groupId);
    Map<String, String> replacements = new HashMap<>();
    replacements.put("groupId", localGroupId);

    try {
      LOG.debug("Querying getMembers API for groupId: {}", localGroupId);
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

  @Override
  public List<Member> getMembers(String personId, String groupId) {
    return getMembers(groupId);
  }

  private ResponseEntity<String> getGrouperResponse(String soap, String action) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_XML);
    headers.set(SOAP_ACTION, action);

    HttpEntity<String> entity = new HttpEntity<>(soap, headers);

    return restTemplate.exchange(configuration.url, HttpMethod.POST, entity, String.class);
  }

  private String replaceTokens(String soapTemplate, Map<String, String> replacements) throws IOException {
    String xml = getTemplate(soapTemplate);
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

  private String getTemplate(String soapTemplate) throws IOException {
    String xml = soapTemplates.get(soapTemplate);
    if (xml == null) {
      xml = StreamUtils.copyToString(new ClassPathResource(soapTemplate).getInputStream(), charSet);
      soapTemplates.put(soapTemplate, xml);
    }
    return xml;
  }
}
