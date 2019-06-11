package com.andersenlab.payment.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class OAuthResourceServerConfig implements ResourceServerConfigurer {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public ResourceServerTokenServices tokenService() {
        /*
         * DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
         * defaultTokenServices.setTokenStore(tokenStore());
         * defaultTokenServices.setAuthenticationManager(authenticationManager); return
         * defaultTokenServices;
         */
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl(
                "http://localhost:9091/oauth/check_token");
        tokenServices.setClientId("payment");
        tokenServices.setClientSecret("AppSecret");
        return tokenServices;
    }

    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setAuthenticationManager(authenticationManager);
        return defaultTokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId("payment")
                .tokenServices(tokenService())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().anyRequest().hasAuthority("ROLE_admin");
        // .and()
        // .authorizeRequests().antMatchers(HttpMethod.POST,
        // "/api/rest/payment/**").hasAuthority("CAN_PAYMENT");
    }
}
