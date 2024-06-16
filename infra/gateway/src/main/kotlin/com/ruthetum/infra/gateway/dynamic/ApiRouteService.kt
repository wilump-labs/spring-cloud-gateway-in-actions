package com.ruthetum.infra.gateway.dynamic

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApiRouteService {
    fun getAll(): Flux<ApiRoute>
    fun getById(id: Long): Mono<ApiRoute>
    fun create(apiRoute: ApiRoute): Mono<ApiRoute>
}