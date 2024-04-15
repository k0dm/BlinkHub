package com.bugbender.ecommerce.catalog.service

import com.bugbender.ecommerce.catalog.model.data.ProductEntity
import org.springframework.stereotype.Service

interface CatalogService {

    fun userProducts(id: String): List<ProductEntity>

    @Service
    class Base() : CatalogService {

        override fun userProducts(id: String): List<ProductEntity> {

            return listOf(
                ProductEntity(
                    productId = "1",
                    userId = id,
                    name = "Product 1",
                    description = "Description for product 1"
                ),
                ProductEntity(
                    productId = "2",
                    userId = id,
                    name = "Product 2",
                    description = "Description for product 2"
                )
            )
        }
    }
}