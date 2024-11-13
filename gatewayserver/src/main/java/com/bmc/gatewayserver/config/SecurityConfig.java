package com.bmc.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
//                Also the roles should be configured in auth server (KEYCLOAK)
                        .pathMatchers("/bankSystem/accounts/**").hasRole("ACCOUNTS")
                        .pathMatchers("/bankSystem/cards/**").hasRole("CARDS")
                        .pathMatchers("/bankSystem/loans/**").hasRole("LOANS")
        ).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                .jwt(jwtspec -> jwtspec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));

//        csrf protection is only needed when browsers are involved.
//        in our scenario there is no browser involved
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new KeycloakRoleConverter()
        );
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
