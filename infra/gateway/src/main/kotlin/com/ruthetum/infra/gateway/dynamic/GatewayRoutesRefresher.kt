package com.ruthetum.infra.gateway.dynamic

import org.springframework.cloud.gateway.event.RefreshRoutesEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component


@Profile("dynamic")
@Component
class GatewayRoutesRefresher(
    private var applicationEventPublisher: ApplicationEventPublisher,
) : ApplicationEventPublisherAware {
    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher
    }

    // TODO. poling with @scheduled
    // DB 테이블 내에 버전을 확인, 변경되었을 경우 refreshRoutes() 호출


    fun refreshRoutes() {
        applicationEventPublisher.publishEvent(RefreshRoutesEvent(this))
    }
}