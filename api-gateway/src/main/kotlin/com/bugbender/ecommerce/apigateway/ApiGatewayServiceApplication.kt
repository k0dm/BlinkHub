package com.bugbender.ecommerce.apigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiGatewayServiceApplication

fun main(args: Array<String>) {
	runApplication<ApiGatewayServiceApplication>(*args)
}
