package com.bugbender.ecommerce.users

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class UsersApplication {

    @Bean
    fun encoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun httpTraceRepository(): HttpExchangeRepository = InMemoryHttpExchangeRepository()
}

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
