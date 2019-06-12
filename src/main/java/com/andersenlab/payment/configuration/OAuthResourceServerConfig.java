package com.andersenlab.payment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class OAuthResourceServerConfig implements ResourceServerConfigurer {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${payment.oauth2.autorization-endpoint}")
    String authorizationEndpoint;

    @Value("${payment.oauth2.client-id}")
    String clientId;

    @Value("${payment.oauth2.resource-id}")
    String resourceId;

    @Bean
    public ResourceServerTokenServices tokenService() {

        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl(
                authorizationEndpoint);
        tokenServices.setClientId(clientId);
        tokenServices.setClientSecret("AppSecret");
        return tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenService())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll();
        // .authorizeRequests().antMatchers(HttpMethod.POST,
        // "/api/rest/payment/**")
        // .hasAuthority("ROLE_admin");
    }
}
