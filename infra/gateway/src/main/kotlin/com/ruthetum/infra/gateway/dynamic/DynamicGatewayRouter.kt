package com.ruthetum.infra.gateway.dynamic

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Profile("dynamic")
@Configuration
class DynamicGatewayRouter(
    private val handler: ApiRouteHandler,
) {
    @Bean
    fun route(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            GET("/routes/refresh"),
            handler::refreshRoutes,
        ).andRoute(
            POST("/routes"),
            handler::create,
        ).andRoute(
            GET("/routes"),
            handler::findAll,
        ).andRoute(
            GET("/routes/{routeId}"),
            handler::findById,
        )
    }
}