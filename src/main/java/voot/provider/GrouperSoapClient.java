package voot.provider;

import com.google.common.collect.ImmutableMap;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import voot.valueobject.Group;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrouperSoapClient implements Provider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  private final WebServiceTemplate webServiceTemplate;

  private final NamespaceContext grouperNameSpaceContext = new GrouperNameSpaceContext();

  private final Pattern replacementPattern = Pattern.compile("\\[(.+?)\\]");

  private final Charset charSet = Charset.forName("UTF-8");

  private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  public GrouperSoapClient(Configuration configuration) {

    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
    httpComponentsMessageSender.setReadTimeout(configuration.timeOutMillis);
    getCredentialsProvider(httpComponentsMessageSender).setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(configuration.credentials.username, configuration.credentials.password));

    webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMessageSender(httpComponentsMessageSender);
    webServiceTemplate.setDefaultUri(configuration.url);

    factory.setNamespaceAware(true);
  }

  @SuppressWarnings("deprecation")
  private CredentialsProvider getCredentialsProvider(HttpComponentsMessageSender httpComponentsMessageSender) {
    return ((DefaultHttpClient) httpComponentsMessageSender.getHttpClient()).getCredentialsProvider();
  }

  @Override
  public boolean shouldBeQueriedFor(String schacHomeOrganization) {
    return true;
  }

  @Override
  public List<Group> getMemberships(final String uid) {
    ImmutableMap replacements = ImmutableMap.<String, String>of("subjectId", uid);

    try {
      LOG.debug("Querying grouper for uid/subjectId: {}", uid);
      String soap = replaceTokens("soap/GetGroups.xml", replacements);
      StringResult result = new StringResult();
      webServiceTemplate.sendSourceAndReceiveToResult(new StreamSource(new StringReader(soap)), result);

      Document document = factory.newDocumentBuilder().parse(new InputSource(new StringReader(result.toString())));

      XPath xpath = XPathFactory.newInstance().newXPath();
      xpath.setNamespaceContext(grouperNameSpaceContext);

      NodeList nodes = (NodeList) xpath.evaluate("//ns:wsGroups", document, XPathConstants.NODESET);
      List<Group> groups = new ArrayList<Group>();

      for (int i = 0; i < nodes.getLength(); i++) {
        Node item = nodes.item(i);
        groups.add(new Group(xpath.evaluate("ns:description", item), xpath.evaluate("ns:name", item)));
      }

      LOG.debug("Grouper result: {} groups.", groups.size());
      return groups;

    } catch (Exception exception) {
      LOG.warn("Failed to invoke grouper, cause: {}, returning empty result.", exception.getMessage());
      return Collections.emptyList();
    }
  }

  private String replaceTokens(String soapTemplate, Map<String, String> replacements) throws IOException {
    String text = StreamUtils.copyToString(new ClassPathResource(soapTemplate).getInputStream(), charSet);
    Matcher matcher = replacementPattern.matcher(text);
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
