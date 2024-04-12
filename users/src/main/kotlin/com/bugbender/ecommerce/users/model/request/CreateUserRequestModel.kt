package com.bugbender.ecommerce.users.model.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class CreateUserRequestModel(
    @NotNull("First name can't be null")
    @Size(min = 3, message = "First name must have more than 3 characters")
    val firstName: String,
    @NotNull("Last name can't be null")
    @Size(min = 3, message = "First name must have more than 3 characters")
    val lastName: String,
    @NotNull
    @Email
    val email: String,
    @NotNull
    @Size(min = 8, max = 16, message = "Password must have 8 .. 16 characters")
    val password: String
)