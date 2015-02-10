package voot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@SpringBootApplication()
public class VootServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VootServiceApplication.class, args);
  }

  private static final String RESOURCE_ID = "groups";

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http
        // Since we want the protected resources to be accessible in the UI as well we need
        // session creation to be allowed (it's disabled by default in 2.0.6)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .requestMatchers().antMatchers("/me/**")
        .and()
        .authorizeRequests()
        .antMatchers("/me/**").access("#oauth2.isOAuth()");
    }

  }
}
