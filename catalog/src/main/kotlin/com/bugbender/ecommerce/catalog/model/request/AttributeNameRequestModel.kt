package com.bugbender.ecommerce.catalog.model.request

import org.jetbrains.annotations.NotNull

data class AttributeNameRequestModel(
    @NotNull("Name can't be null")
    val name: String,
)


