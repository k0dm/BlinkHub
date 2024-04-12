package com.bugbender.ecommerce.users.model.shared

import java.util.UUID

data class UserDto(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    val userId: String = UUID.randomUUID().toString(),
    var encryptedPassword: String = ""
)