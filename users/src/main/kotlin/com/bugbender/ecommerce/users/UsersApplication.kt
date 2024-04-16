package com.bugbender.ecommerce.users

import feign.Logger
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableFeignClients
class UsersApplication {

    @Bean
    fun encoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun httpTraceRepository(): HttpExchangeRepository = InMemoryHttpExchangeRepository()

    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL

//    @Bean
//    fun feignErrorDecoder(): FeignErrorDecoder = FeignErrorDecoder()
}

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}