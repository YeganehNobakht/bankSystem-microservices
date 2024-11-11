package com.bmc.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers("/bankSystem/accounts/**").authenticated()
                .pathMatchers("/bankSystem/cards/**").authenticated()
                .pathMatchers("/bankSystem/loans/**").authenticated()
        ).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                .jwt(Customizer.withDefaults()));

//        csrf protection is only needed when browsers are involved.
//        in our scenario there is no browser involved
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }
}
