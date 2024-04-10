package com.bugbender.ecommerce.users.controllers

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val environment: Environment) {

    @GetMapping("/status/check")
    fun status() = "users-service working on ${environment.getProperty("local.server.port")}"
}