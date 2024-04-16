package com.bugbender.ecommerce.users.model.response

data class UserResponseModel(
    var userId: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var products: List<ProductResponseModel> = listOf()
)

