package com.bmc.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator bankSystemRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/banksystem/accounts/**")
						.filters( f -> f.rewritePath("/banksystem/accounts/(?<segment>.*)","/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/banksystem/loans/**")
						.filters( f -> f.rewritePath("/banksystem/loans/(?<segment>.*)","/${segment}"))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/banksystem/cards/**")
						.filters( f -> f.rewritePath("/banksystem/cards/(?<segment>.*)","/${segment}"))
						.uri("lb://CARDS")).build();


	}

}
