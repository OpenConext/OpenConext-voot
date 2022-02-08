package voot.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    @Order(1)
    public static class ResourceServerWebSecurity extends WebSecurityConfigurerAdapter {


        @Value("${oidcng.checkToken.endpoint_url}")
        private String introspectionUri;

        @Value("${oidcng.checkToken.clientId}")
        private String clientId;

        @Value("${oidcng.checkToken.secret}")
        private String secret;

        @Value("${checkToken.cache}")
        private boolean cacheTokens;

        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
            web.ignoring()
                    .antMatchers("/internal/health", "/internal/info", "/health", "/info");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().configurationSource(new OidcCorsConfigurationSource()).configure(http);
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf().disable()
                    .httpBasic().disable()
                    .anonymous()
                    .and()
                    .authorizeRequests(authz -> authz
                            .antMatchers("/internal/all-groups").hasAuthority("SCOPE_all-groups")
                            .antMatchers("/me/**", "/groups/**", "/internal/**").hasAuthority("SCOPE_groups")
                            .antMatchers("/members/**").hasAuthority("SCOPE_members")
                            .anyRequest().authenticated())
                    .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(token -> token
                            .introspector(new CachingOpaqueTokenIntrospector(introspectionUri, clientId, secret, cacheTokens))));
        }
    }

    @Configuration
    @Order(2)
    public static class MvcConfig implements WebMvcConfigurer {

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(new UserHandlerMethodArgumentResolver());
        }
    }

}
