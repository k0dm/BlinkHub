package com.bugbender.ecommerce.apigateway

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

@Component
class AuthorizationHeaderFilter(
    private val environment: Environment
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    private val tokenSecret = environment.getProperty("token.secret")!!
    private val secretKey = Keys.hmacShaKeyFor(tokenSecret.toByteArray())

    class Config

    override fun apply(config: Config) = GatewayFilter { exchange, chain ->
        val headers = exchange.request.headers
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            return@GatewayFilter onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED)
        }

        val jwt = headers[HttpHeaders.AUTHORIZATION]!!.first().substringAfter("Bearer ")

        if (!isJwtValid(jwt)) {
            return@GatewayFilter onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED)
        }

        chain.filter(exchange)
    }

    private fun onError(exchange: ServerWebExchange, message: String, httpStatus: HttpStatus): Mono<Void> {
        val response = exchange.response
        response.setStatusCode(httpStatus)
        return response.setComplete()
    }

    private fun isJwtValid(jwt: String): Boolean {
        return !extractSubject(jwt).isNullOrBlank()
    }

    private fun extractSubject(token: String): String? = getAllClaims(token).subject

    private fun isExpired(token: String): Boolean =
        getAllClaims(token).expiration.before(Date(System.currentTimeMillis()))

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token).payload
    }
}