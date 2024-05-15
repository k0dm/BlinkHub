package com.bugbender.ecommerce.users.data

import com.bugbender.ecommerce.users.model.response.ProductResponseModel
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient("catalog-service")
interface CatalogServiceClient {

    @GetMapping("/users/{id}/products")
    fun getProducts(
        @PathVariable("id") userId: String,
        @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String
    ): List<ProductResponseModel>
}