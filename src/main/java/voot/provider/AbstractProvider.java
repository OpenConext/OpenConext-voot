package voot.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import voot.util.UrnUtils;

import java.net.MalformedURLException;
import java.util.function.Function;
import java.util.regex.Matcher;

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


    @SneakyThrows
    public AbstractProvider(Configuration configuration) {
        this.configuration = configuration;
        this.restTemplate = new RestTemplate(getRequestFactory());
        restTemplate.setErrorHandler(new ProviderResponseErrorHandler());
        this.groupIdPrefix = String.format("urn:collab:group:%s:", configuration.schacHomeOrganization);
        LOG.debug("Initializing {} {}", getClass(), configuration);
    }

    @Override
    public boolean isExternalGroupProvider() {
        return !configuration.type.equals(GroupProviderType.TEAMS);
    }

    @Override
    public boolean shouldBeQueriedForGroup(String groupId) {
        Matcher matcher = UrnUtils.GROUP_PATTERN.matcher(groupId);
        /*
         * For unqualified group names we do NOT query External Group Providers. This is a design decision discussed with SURFnet
         */
        return matcher.matches() && matcher.group(1).equals(configuration.schacHomeOrganization);
    }

    @Override
    public boolean shouldBeQueriedForMembers(String groupId) {
        return !isExternalGroupProvider();
    }

    @SneakyThrows
    protected <T> T parseJson(String json, Class<T> t) {
        return objectMapper.readValue(json, t);
    }

    protected <T, U> T handleResponse(ResponseEntity<U> response, Function<U, T> parseFunction, String methodName, T defaultValue) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return parseFunction.apply(response.getBody());
        } else {
            LOG.warn("Failed to invoke {}. Reponse is {} with configuration {}, returning empty result.", methodName, response, configuration);
            return defaultValue;
        }
    }

    private ClientHttpRequestFactory getRequestFactory() throws MalformedURLException {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(null, -1),
                new UsernamePasswordCredentials(configuration.credentials.username, configuration.credentials.password.toCharArray()));
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofSeconds(10))
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(Timeout.ofMilliseconds(configuration.timeOutMillis))
                        .setConnectTimeout(Timeout.ofMilliseconds(configuration.timeOutMillis))
                        .setResponseTimeout(Timeout.ofMilliseconds(configuration.timeOutMillis))
                        .build())
                .build();
        return new PreemptiveAuthenticationHttpComponentsClientHttpRequestFactory(httpClient, configuration.url,
                new UsernamePasswordCredentials(configuration.credentials.username, configuration.credentials.password.toCharArray()));
    }

    @Override
    public String toString() {
        return String.format("Provider with configuration: %s", this.configuration);
    }
}
