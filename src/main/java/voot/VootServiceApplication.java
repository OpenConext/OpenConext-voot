package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import voot.oauth.*;
import voot.provider.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@SpringBootApplication()
public class VootServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VootServiceApplication.class, args);
  }

  @Autowired
  private ResourceLoader resourceLoader;

  @Bean
  @Autowired
  public ExternalGroupsService externalGroupsService(
    @Value("${externalProviders.config.path}") final String configFileLocation) throws IOException {

    Yaml yaml = new Yaml(new SafeConstructor());

    @SuppressWarnings("unchecked")
    Map<String, List<Map<String, Object>>> config = (Map<String, List<Map<String, Object>>>) yaml.load(resourceLoader.getResource(configFileLocation).getInputStream());
    final List<Map<String, Object>> externalGroupProviders = config.get("externalGroupProviders");

    final List<Provider> groupClients = externalGroupProviders.stream().map(entryMap -> {
      final String type = (String) entryMap.get("type");
      final String url = StringUtils.trimTrailingCharacter((String) entryMap.get("url"), '/');
      final String schacHomeOrganization = (String) entryMap.get("schacHomeOrganization");
      final String name = (String) entryMap.get("name");
      final Integer timeoutMillis = (Integer) entryMap.get("timeoutMillis");
      @SuppressWarnings("unchecked")
      final Map<String, Object> rawCredentials = (Map<String, Object>) entryMap.get("credentials");
      String username = (String) rawCredentials.get("username");
      String secret = (String) rawCredentials.get("secret");

      GroupProviderType groupProviderType = GroupProviderType.valueOf(type.toUpperCase());

      final Provider.Configuration configuration = new Provider.Configuration(groupProviderType, url, new Provider.Configuration.Credentials(username, secret), timeoutMillis, schacHomeOrganization, name);
      switch (groupProviderType) {
        case VOOT2:
          return new Voot2Provider(configuration);
        case OPEN_SOCIAL:
          return new OpenSocialClient(configuration);
        case GROUPER:
          return new GrouperSoapClient(configuration);
        default:
          throw new IllegalArgumentException("Unknown external provider-type: " + type);
      }
    }).collect(Collectors.toList());
    return new ExternalGroupsService(groupClients);
  }

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceServerConfiguration.class);

    @Value("${vootservice.oauthResourceId}")
    private String resourceId;

    @Value("${authz.checkToken.endpoint.url}")
    private String authzCheckTokenEndpointUrl;

    @Value("${authz.checkToken.clientId}")
    private String authzCheckTokenClientId;

    @Value("${authz.checkToken.secret}")
    private String authzCheckTokenSecret;

    @Value("${oidc.checkToken.endpoint.url}")
    private String oidcCheckTokenEndpointUrl;

    @Value("${oidc.checkToken.clientId}")
    private String oidcCheckTokenClientId;

    @Value("${oidc.checkToken.secret}")
    private String oidcCheckTokenSecret;

    @Value("${vootservice.requiredScopes}")
    private String spaceDelimitedRequiredScopes;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.resourceId(resourceId).tokenServices(resourceServerTokenServices()).tokenExtractor(tokenExtractor());
    }

    private DecisionResourceServerTokenServices resourceServerTokenServices() {
      CompositeDecisionResourceServerTokenServices tokenServices = new CompositeDecisionResourceServerTokenServices(
        Arrays.asList(oidcResourceServerTokenServices(), authzResourceServerTokenServices())
      );
      return new CachedRemoteTokenServices(tokenServices, 1000 * 60 * 5, 1000 * 60 * 5);
    }

    private DecisionResourceServerTokenServices oidcResourceServerTokenServices() {
      return new OidcRemoteTokenServices(oidcCheckTokenEndpointUrl, oidcCheckTokenClientId, oidcCheckTokenSecret);
    }

    private DecisionResourceServerTokenServices authzResourceServerTokenServices() {
      AuthzResourceServerTokenServices authzResourceServerTokenServices = new AuthzResourceServerTokenServices();
      authzResourceServerTokenServices.setCheckTokenEndpointUrl(authzCheckTokenEndpointUrl);
      authzResourceServerTokenServices.setClientId(authzCheckTokenClientId);
      authzResourceServerTokenServices.setClientSecret(authzCheckTokenSecret);

      final DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
      accessTokenConverter.setUserTokenConverter(new AuthzSchacHomeAwareUserAuthenticationConverter());
      authzResourceServerTokenServices.setAccessTokenConverter(accessTokenConverter);
      return authzResourceServerTokenServices;
    }

    /*
     * Voot Service: explicitly deny other means of supplying oauth token than "bearer"
     */
    private TokenExtractor tokenExtractor() {
      return new BearerTokenExtractor() {
        protected String extractToken(HttpServletRequest request) {
          // only check the header...
          return extractHeaderToken(request);
        }
      };
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      final List<String> requiredScopes = Arrays.asList(spaceDelimitedRequiredScopes.split(" "));

      final String hasScopeArgs = requiredScopes.stream().
        map(str -> "'" + str + "'").
        collect(joining(","));
      LOG.debug("Will require the following approved scopes when handling requests: {}", hasScopeArgs);
      http.
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).
        and().
        authorizeRequests().
        antMatchers("/me/**", "groups/**", "internal/**").access("#oauth2.hasScope(" + hasScopeArgs + ")");
    }

  }
}
