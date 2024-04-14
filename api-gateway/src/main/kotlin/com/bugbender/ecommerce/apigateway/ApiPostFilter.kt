package com.bugbender.ecommerce.apigateway

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class ApiPostFilter : GlobalFilter, Ordered {

    private val logger = LoggerFactory.getLogger(ApiPostFilter::class.java)

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        return chain.filter(exchange).then(Mono.fromRunnable {
            logger.info("ApiPostFilter called")
        })
    }

    override fun getOrder() = 0
}