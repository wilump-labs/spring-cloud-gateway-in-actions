package com.ruthetum.infra.gateway.dynamic

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class ApiRoute(
    val routeIdentifier: String,
    val uri: String,
    val method: String,
    val path: String,
    @Id
    val id: Long?
)