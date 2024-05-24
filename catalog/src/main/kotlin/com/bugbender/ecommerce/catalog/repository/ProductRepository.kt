package com.bugbender.ecommerce.catalog.repository

import com.bugbender.ecommerce.catalog.model.data.ProductEntity
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<ProductEntity, Long>{
}

