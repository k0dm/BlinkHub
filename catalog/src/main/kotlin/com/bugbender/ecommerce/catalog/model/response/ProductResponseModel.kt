package com.bugbender.ecommerce.catalog.model.response

data class ProductResponseModel(
    var productId: String = "",
    var userId: String = "",
    var name: String = "",
    var description: String = ""
)