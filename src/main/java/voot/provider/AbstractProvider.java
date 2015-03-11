package voot.provider;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractProvider implements Provider {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractProvider.class);

  private static final Pattern groupPattern = Pattern.compile("^urn:collab:group:([^:]+):(.*)$");
  private static final Pattern personPattern = Pattern.compile("^urn:collab:person:([^:]+):(.*)$");


  private final RestTemplate restTemplate;
  private final Configuration configuration;

  public AbstractProvider(Configuration configuration) {
    this.configuration = configuration;
    this.restTemplate = new RestTemplate(getRequestFactory());
    LOG.debug("Initializing {} {}",getClass(), configuration);
  }

  protected RestTemplate getRestTemplate() {
    return restTemplate;
  }

  protected Configuration getConfiguration() {
    return configuration;
  }

  public String stripGroupUrnIdentifier(String groupId) {
    Matcher m = groupPattern.matcher(groupId) ;
    return m.matches() ? m.group(2) : groupId;
  }

  public String stripPersonUrnIdentifier(String uid) {
    Matcher m = personPattern.matcher(uid) ;
    return m.matches() ? m.group(2) : uid;
  }

  public boolean shouldBeQueriedFor(String schacHomeOrganization) {
    return this.configuration.type.equals(GroupProviderType.GROUPER) || getSchacHomeOrganization().equals(schacHomeOrganization);
  }

  public String getSchacHomeOrganization() {
    return this.configuration.schacHomeOrganization;
  }

  private ClientHttpRequestFactory getRequestFactory() {
    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
    basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(configuration.credentials.username, configuration.credentials.password));
    httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
    httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(configuration.timeOutMillis).setConnectTimeout(configuration.timeOutMillis).setSocketTimeout(configuration.timeOutMillis).build());
    CloseableHttpClient httpClient = httpClientBuilder.build();
    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }

}
