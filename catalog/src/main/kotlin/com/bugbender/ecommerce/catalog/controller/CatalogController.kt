package com.bugbender.ecommerce.catalog.controller

import com.bugbender.ecommerce.catalog.model.response.ProductResponseModel
import com.bugbender.ecommerce.catalog.service.CatalogService
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{id}/products")
class CatalogController(private val service: CatalogService) {

    private val modelMapper = ModelMapper()

    @GetMapping
    fun userProducts(@PathVariable id: String): List<ProductResponseModel> {
        val productEntities = service.userProducts(id = id)
        return modelMapper.map(productEntities, object : TypeToken<List<ProductResponseModel>>() {}.type)
    }
}