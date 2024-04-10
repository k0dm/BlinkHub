package com.bugbender.ecommerce.accountmanagement.controllers

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account-management")
class AccountManagementController(private val environment: Environment) {

    @GetMapping
    fun status() = "account-management-service on port: ${environment.getProperty("local.server.port")}"
}