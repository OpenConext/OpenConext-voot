package voot.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Value("${oidcng.checkToken.endpoint_url}")
    private String introspectionUri;

    @Value("${oidcng.checkToken.clientId}")
    private String clientId;

    @Value("${oidcng.checkToken.secret}")
    private String secret;

    @Value("${checkToken.cache}")
    private boolean cacheTokens;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(new OidcCorsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .anonymous(Customizer.withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/internal/health", "/internal/info", "/health", "/info", "/error").permitAll()
                        .requestMatchers("/internal/all-groups").hasAuthority("SCOPE_all-groups")
                        .requestMatchers("/me/**", "/groups/**", "/internal/**").hasAuthority("SCOPE_groups")
                        .requestMatchers("/members/**").hasAuthority("SCOPE_members")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(token -> token
                        .introspector(new CachingOpaqueTokenIntrospector(introspectionUri, clientId, secret, cacheTokens))));
        return http.build();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserHandlerMethodArgumentResolver());
    }

}
