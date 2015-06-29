package voot.provider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractProvider implements Provider {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractProvider.class);

  /*
   * We can't share the RestTemplate among Providers as we tie the BasicCredentialsProvider with the configured user / password
   * and those are Provider specific
   */
  protected final RestTemplate restTemplate;
  protected final Configuration configuration;
  protected final String groupIdPrefix;

  /*
   * ObjectMapper is thread-safe (http://wiki.fasterxml.com/JacksonFAQ)
   */
  protected static final ObjectMapper objectMapper = new ObjectMapper().
    enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).
    setSerializationInclusion(JsonInclude.Include.NON_NULL);


  public AbstractProvider(Configuration configuration) {
    this.configuration = configuration;
    this.restTemplate = new RestTemplate(getRequestFactory());
    this.groupIdPrefix = String.format("urn:collab:group:%s:", configuration.schacHomeOrganization);
    LOG.debug("Initializing {} {}", getClass(), configuration);
  }

  @Override
  public boolean isExternalGroupProvider() {
    return !configuration.type.equals(GroupProviderType.GROUPER);
  }

  protected <T> T parseJson(String json, Class<T> t) {
    try {
      return objectMapper.readValue(json, t);
    } catch (IOException e) {
      throw new RuntimeException("Error parsing Json", e);
    }
  }

  private ClientHttpRequestFactory getRequestFactory() {
    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().evictExpiredConnections().evictIdleConnections(10l, TimeUnit.SECONDS);
    BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
    basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(configuration.credentials.username, configuration.credentials.password));
    httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
    httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(configuration.timeOutMillis).setConnectTimeout(configuration.timeOutMillis).setSocketTimeout(configuration.timeOutMillis).build());

    CloseableHttpClient httpClient = httpClientBuilder.build();
    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }

  @Override
  public String toString() {
    return String.format("Provider with configuration: %s", this.configuration);
  }
}
