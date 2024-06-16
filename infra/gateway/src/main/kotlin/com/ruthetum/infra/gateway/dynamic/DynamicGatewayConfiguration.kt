package com.ruthetum.infra.gateway.dynamic

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("dynamic")
@Configuration
class DynamicGatewayConfiguration(
    private val routeLocatorBuilder: RouteLocatorBuilder,
    private val apiRouteService: ApiRouteService,
) {
    @Bean
    fun routeLocator(): RouteLocator {
        return RouteLocatorImpl(routeLocatorBuilder, apiRouteService)
    }
}