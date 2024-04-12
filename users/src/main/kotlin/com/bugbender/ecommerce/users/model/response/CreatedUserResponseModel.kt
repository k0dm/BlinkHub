package com.bugbender.ecommerce.users.model.response

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class CreatedUserResponseModel(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
)