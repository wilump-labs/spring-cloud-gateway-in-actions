package com.ruthetum.infra.gateway.dynamic

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Profile("dynamic")
@Service
class ApiRouteServiceImpl(
    private val apiRouteRepository: ApiRouteRepository,
) : ApiRouteService {
    override fun getAll(): Flux<ApiRoute> {
        return apiRouteRepository.findAll()
    }

    override fun getById(id: Long): Mono<ApiRoute> {
        return apiRouteRepository.findById(id)
    }

    override fun create(apiRoute: ApiRoute): Mono<ApiRoute> {
        return this.apiRouteRepository.save(apiRoute)
    }
}