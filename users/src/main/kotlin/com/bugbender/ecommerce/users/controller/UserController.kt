package com.bugbender.ecommerce.users.controller

import com.bugbender.ecommerce.users.model.request.CreateUserRequestModel
import com.bugbender.ecommerce.users.model.response.CreatedUserResponseModel
import com.bugbender.ecommerce.users.model.response.UserResponseModel
import com.bugbender.ecommerce.users.model.shared.UserDto
import com.bugbender.ecommerce.users.service.UserService
import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val service: UserService,
    private val environment: Environment
) {

    private val mapper = ModelMapper()

    init {
        mapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
    }

    @PostMapping
    fun createUser(@Valid @RequestBody userDetails: CreateUserRequestModel): ResponseEntity<CreatedUserResponseModel> {
        val userDto = mapper.map(userDetails, UserDto::class.java)

        val createdUserDto = service.createUser(userDto)
        val responseModel = mapper.map(createdUserDto, CreatedUserResponseModel::class.java)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel)
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable("userId") userId: String): ResponseEntity<UserResponseModel> {
        val userDto = service.getUserByUserId(userId)
        val responseModel = mapper.map(userDto, UserResponseModel::class.java)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel)
    }

    @GetMapping("/status")
    fun status() =
        "users-service working on ${environment.getProperty("local.server.port")} token-exp=${environment.getProperty("token.expiration_time")}"
}