package com.ruthetum.infra.gateway.dynamic

import org.springframework.context.annotation.Profile
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Profile("dynamic")
@Repository
interface ApiRouteRepository : ReactiveCrudRepository<ApiRoute, Long>