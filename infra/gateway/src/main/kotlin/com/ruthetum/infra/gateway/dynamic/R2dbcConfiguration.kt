package com.ruthetum.infra.gateway.dynamic

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Profile("dynamic")
@Configuration
@EnableR2dbcRepositories
@EnableR2dbcAuditing
class R2dbcConfiguration