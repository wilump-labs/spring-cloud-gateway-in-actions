package com.ruthetum.infra.gateway.dynamic

import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Profile("dynamic")
@Component
class ApiRouteHandler(
    private val apiRouteService: ApiRouteService,
    private val gatewayRoutesRefresher: GatewayRoutesRefresher,
) {
    fun create(serverRequest: ServerRequest): Mono<ServerResponse> {
        val apiRoute = serverRequest.bodyToMono(ApiRoute::class.java)
            .flatMap { route: ApiRoute ->
                apiRouteService.create(route) }
        return apiRoute.flatMap { route: ApiRoute ->
            ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(route))
        }
    }

    fun findAll(serverRequest: ServerRequest): Mono<ServerResponse> {
        val routes = apiRouteService.getAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromPublisher(routes, ApiRoute::class.java))
    }

    fun findById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val apiId = serverRequest.pathVariable("routeId")
        val apiRoute: Mono<ApiRoute> = apiRouteService.getById(apiId.toLong())
        return apiRoute.flatMap { route: ApiRoute ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(route))
        }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun refreshRoutes(request: ServerRequest): Mono<ServerResponse> {
        gatewayRoutesRefresher.refreshRoutes()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue("Routes refreshed"))
    }

    // TODO. get routing list, create new route, update route, delete route
}