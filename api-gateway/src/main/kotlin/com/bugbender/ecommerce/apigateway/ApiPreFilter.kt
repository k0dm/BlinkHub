package com.bugbender.ecommerce.apigateway

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class ApiPreFilter : GlobalFilter {

    private val logger = LoggerFactory.getLogger(ApiPreFilter::class.java)

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        logger.info("ApiPreFilter called")
        logger.info("RequestPath = ${exchange.request.path}")


        val headers = exchange.request.headers
        headers.keys.forEach {
            logger.info("$it = ${headers.getFirst(it)}")
        }

        return chain.filter(exchange)
    }
}