package voot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@SpringBootApplication()
public class VootServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VootServiceApplication.class, args);
  }

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${vootservice.oauthResourceId}")
    private String resourceId;

    @Value("${oauth.checkToken.endpoint.url}")
    private String checkTokenEndpointUrl;

    @Value("${oauth.checkToken.clientId}")
    private String checkTokenClientId;

    @Value("${oauth.checkToken.secret}")
    private String checkTokenSecret;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.resourceId(resourceId).tokenServices(tokenServices());
    }

    private RemoteTokenServices tokenServices() {
      final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
      remoteTokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
      remoteTokenServices.setClientId(checkTokenClientId);
      remoteTokenServices.setClientSecret(checkTokenSecret);
      return remoteTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .authorizeRequests()
        .antMatchers("/**").access("#oauth2.hasScope('read')");
    }
  }
}
