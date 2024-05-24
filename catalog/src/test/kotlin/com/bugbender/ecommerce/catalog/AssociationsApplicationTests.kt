package com.bugbender.ecommerce.catalog

import com.bugbender.ecommerce.catalog.model.data.ProductCategoryEntity
import com.bugbender.ecommerce.catalog.model.data.ProductEntity
import com.bugbender.ecommerce.catalog.model.data.ProductStatusEntity
import com.bugbender.ecommerce.catalog.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AssociationsApplicationTests {

    @Autowired
    private lateinit var productRepository: ProductRepository

    //todo fix it
    fun createProduct() {
        val product = ProductEntity(
            productId = 1,
            name = "test1",
            description = "test2",
            sku="test1",
            originalPrice = 1.0,
            salePrice = 0.9,
            qty = 10,
            status = ProductStatusEntity(1, name = "TEST"),
            category = ProductCategoryEntity(1, "test1")
        )
    }
}


