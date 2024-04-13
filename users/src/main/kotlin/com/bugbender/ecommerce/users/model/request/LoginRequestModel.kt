package com.bugbender.ecommerce.users.model.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class LoginRequestModel(
    @NotNull
    @Email
    var email: String = "",
    @NotNull
    @Size(min = 8, max = 16, message = "Password must have 8 .. 16 characters")
    var password: String =""
)