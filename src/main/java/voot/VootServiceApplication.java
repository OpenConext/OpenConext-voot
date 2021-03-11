package voot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.TraceWebFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import voot.oauth.CachedRemoteTokenServices;
import voot.oauth.CompositeDecisionResourceServerTokenServices;
import voot.oauth.DecisionResourceServerTokenServices;
import voot.oidcng.RemoteTokenServices;
import voot.provider.GroupProviderType;
import voot.provider.OpenSocialClient;
import voot.provider.OpenSocialMembersClient;
import voot.provider.Provider;
import voot.provider.TeamsProviderClient;
import voot.provider.Voot2Provider;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {TraceWebFilterAutoConfiguration.class, MetricFilterAutoConfiguration.class})
public class VootServiceApplication {

  @Autowired
  private ResourceLoader resourceLoader;

  @Value("${support.linkedGrouperExternalGroups}")
  private boolean supportLinkedGrouperExternalGroups;

  public static void main(String[] args) {
    SpringApplication.run(VootServiceApplication.class, args);
  }

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
      @SuppressWarnings("unchecked") final Map<String, Object> rawCredentials = (Map<String, Object>) entryMap.get("credentials");
      String username = (String) rawCredentials.get("username");
      String secret = (String) rawCredentials.get("secret");

      GroupProviderType groupProviderType = GroupProviderType.valueOf(type.toUpperCase());

      final Provider.Configuration configuration = new Provider.Configuration(groupProviderType, url, new Provider.Configuration.Credentials(username, secret), timeoutMillis, schacHomeOrganization, name);
      switch (groupProviderType) {
        case VOOT2:
          return new Voot2Provider(configuration);
        case OPEN_SOCIAL:
          return new OpenSocialClient(configuration);
        case TEAMS:
          return new TeamsProviderClient(configuration);
        case OPEN_SOCIAL_MEMBERS:
          return new OpenSocialMembersClient(configuration);
        default:
          throw new IllegalArgumentException("Unknown external provider-type: " + type);
      }
    }).collect(Collectors.toList());
    return new ExternalGroupsService(groupClients, supportLinkedGrouperExternalGroups);
  }

  @Configuration
  @EnableResourceServer
  @EnableWebSecurity
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${oidcng.checkToken.endpoint.url}")
    private String oidcngCheckTokenEndpointUrl;

    @Value("${oidcng.checkToken.clientId}")
    private String oidcngCheckTokenClientId;

    @Value("${oidcng.checkToken.secret}")
    private String oidcngCheckTokenSecret;

    @Value("${oidcng.checkToken.issuer}")
    private String oidcngCheckTokenIssuer;

    @Value("${checkToken.cache}")
    private boolean checkTokenCache;

    @Value("${checkToken.cache.duration.milliSeconds}")
    private int checkTokenCacheDurationMilliseconds;

    @Value("${checkToken.cache.expiryIntervalCheck.milliseconds}")
    private int expiryIntervalCheckMilliseconds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.resourceId("groups")
        .tokenServices(resourceServerTokenServices())
        .tokenExtractor(tokenExtractor())
        .authenticationEntryPoint(authenticationEntryPoint());
    }

    private OAuth2AuthenticationEntryPoint authenticationEntryPoint() {
      DefaultWebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator() {
        @Override
        public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
          ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
          if (responseEntity.getStatusCode().equals(HttpStatus.UNAUTHORIZED) &&
            "Full authentication is required to access this resource".equals(e.getMessage())) {
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(responseEntity.getHeaders());
            headers.put("WWW-Authenticate", Arrays.asList("Bearer realm=\"groups\""));
            return new ResponseEntity<>(headers, responseEntity.getStatusCode());

          }
          return responseEntity;
        }
      };
      OAuth2AuthenticationEntryPoint authenticationEntryPoint = new SilentOAuth2AuthenticationEntryPoint();
      authenticationEntryPoint.setExceptionTranslator(exceptionTranslator);
      return authenticationEntryPoint;
    }

    private DecisionResourceServerTokenServices resourceServerTokenServices() {
      CompositeDecisionResourceServerTokenServices tokenServices = new CompositeDecisionResourceServerTokenServices(
        Collections.singletonList(oidcNgResourceServerTokenServices())
      );
      return checkTokenCache ?
        new CachedRemoteTokenServices(tokenServices, checkTokenCacheDurationMilliseconds, expiryIntervalCheckMilliseconds) :
        tokenServices;
    }

    private DecisionResourceServerTokenServices oidcNgResourceServerTokenServices() {
      return new RemoteTokenServices(oidcngCheckTokenEndpointUrl, oidcngCheckTokenClientId, oidcngCheckTokenSecret, oidcngCheckTokenIssuer, "schac_home_organization");
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
      http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .authorizeRequests()
        .antMatchers("/me/**", "/groups/**", "/internal/**").access("#oauth2.hasScope('groups')")
        .antMatchers("/members/**").access("#oauth2.hasScope('members')")
        .antMatchers("/internal/all-groups").access("#oauth2.hasScope('all-groups')")
        .antMatchers("/public/**", "/health/**", "/info/**").permitAll()
        .antMatchers("/**").hasRole("USER");
    }

  }

}
