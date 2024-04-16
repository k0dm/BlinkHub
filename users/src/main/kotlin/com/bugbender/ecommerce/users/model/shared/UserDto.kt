package com.bugbender.ecommerce.users.model.shared

import com.bugbender.ecommerce.users.model.response.ProductResponseModel
import java.util.*

data class UserDto(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var userId: String = UUID.randomUUID().toString(),
    var encryptedPassword: String = "",
    var products: List<ProductResponseModel> = listOf()
)