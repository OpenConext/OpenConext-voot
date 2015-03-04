package voot.provider;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.GetGroups;
import edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.GetGroupsResponse;
import edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.ObjectFactory;
import edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup;
import voot.valueobject.Group;

public class GrouperSoapClient implements Provider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  private final WebServiceTemplate webServiceTemplate;


  public GrouperSoapClient(Configuration configuration) {

    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
    httpComponentsMessageSender.setReadTimeout(configuration.timeOutMillis);
    getCredentialsProvider(httpComponentsMessageSender).setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(configuration.credentials.username, configuration.credentials.password));

    webServiceTemplate = new WebServiceTemplate();
    final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setSchema(new ClassPathResource("wsdl/GrouperService_v2_0.wsdl"));
    marshaller.setPackagesToScan("edu.internet2.middleware.grouper.ws.soap_v2_2.xsd");
    webServiceTemplate.setMarshaller(marshaller);
    webServiceTemplate.setUnmarshaller(marshaller);
    webServiceTemplate.setMessageSender(httpComponentsMessageSender);
    webServiceTemplate.setDefaultUri(configuration.url);
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
    final ObjectFactory objectFactory = new ObjectFactory();
    final GetGroups request = new GetGroups();
    request.setClientVersion(objectFactory.createGetGroupsClientVersion("2.0.0"));

    WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
    wsSubjectLookup.setSubjectId(objectFactory.createWsSubjectLookupSubjectIdentifier(uid));

    request.getSubjectLookups().add(wsSubjectLookup);
    try {
      GetGroupsResponse response = (GetGroupsResponse) webServiceTemplate.marshalSendAndReceive(request);

      final List<Group> groups = response.getReturn().getValue().getResults().stream()
        .filter(Objects::nonNull)
        .map(wsGetGroupsResult ->
            wsGetGroupsResult.getWsGroups().stream().
              map(wsGroup -> new Group(wsGroup.getDisplayName().getValue(), wsGroup.getName().getValue()))
        )
        .flatMap(l -> l).collect(Collectors.toList());
      LOG.debug("Grouper result: {} groups.", groups.size());
      return groups;

    } catch (WebServiceIOException exception) {
      LOG.warn("Failed to invoke grouper, cause: {}, returning empty result.", exception.getMessage());
      return Collections.emptyList();
    }
  }
}
