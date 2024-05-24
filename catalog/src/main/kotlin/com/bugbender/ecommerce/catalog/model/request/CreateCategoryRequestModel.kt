package com.bugbender.ecommerce.catalog.model.request

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class CreateCategoryRequestModel(
    @NotNull("Name can't be null")
    @Size(min = 3, max = 255, message = "")
    val name: String,
    val parentId: Long?
)