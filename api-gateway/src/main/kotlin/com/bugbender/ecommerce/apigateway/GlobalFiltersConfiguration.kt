package com.bugbender.ecommerce.apigateway

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import reactor.core.publisher.Mono

@Configuration
class GlobalFiltersConfiguration {

    private val logger = LoggerFactory.getLogger(GlobalFiltersConfiguration::class.java)

    @Order(1)
    @Bean
    fun secondPreFilter(): GlobalFilter = GlobalFilter { exchange, chain ->
        logger.info("SecondPreFilter called")
        chain.filter(exchange).then(Mono.fromRunnable {
            logger.info("SecondPostFilter called")
        })
    }

    @Order(2)
    @Bean
    fun thirdPreFilter(): GlobalFilter = GlobalFilter { exchange, chain ->
        logger.info("ThirdPreFilter called")
        chain.filter(exchange).then(Mono.fromRunnable {
            logger.info("ThirdPostFilter called")
        })
    }
}