package com.ruthetum.infra.gateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("bean")
@Configuration
class BeanGatewayConfiguration {
    @Bean
    fun routeLocator(routeLocatorBuilder: RouteLocatorBuilder): RouteLocator? {
        return routeLocatorBuilder.routes()
            .route("a-service") { route: PredicateSpec ->
                route.path("/a/**").uri("http://localhost:18090")
            }
            .route("b-service") { route: PredicateSpec ->
                route.path("/b/**").uri("http://localhost:18091")
            }
            .build()
    }
}