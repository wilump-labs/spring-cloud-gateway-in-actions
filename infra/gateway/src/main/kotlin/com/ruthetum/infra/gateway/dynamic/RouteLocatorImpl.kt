package com.ruthetum.infra.gateway.dynamic

import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import reactor.core.publisher.Flux

class RouteLocatorImpl(
    private val routeLocatorBuilder: RouteLocatorBuilder,
    private val apiRouteService: ApiRouteService,
) : RouteLocator {
    override fun getRoutes(): Flux<Route> {
        val routesBuilder: RouteLocatorBuilder.Builder = routeLocatorBuilder.routes()
        return apiRouteService.getAll()
            .map { apiRoute ->
                routesBuilder.route(
                    apiRoute.routeIdentifier
                ) { setPredicateSpec(apiRoute, it) }
            }
            .collectList()
            .flatMapMany { routesBuilder.build().routes }

    }

    private fun setPredicateSpec(apiRoute: ApiRoute, predicateSpec: PredicateSpec): Buildable<Route?>? {
        val booleanSpec = predicateSpec.path(apiRoute.path)
        if (apiRoute.method.isNotEmpty()) {
            booleanSpec.and().method(apiRoute.method)
        }
        return booleanSpec.uri(apiRoute.uri)
    }
}