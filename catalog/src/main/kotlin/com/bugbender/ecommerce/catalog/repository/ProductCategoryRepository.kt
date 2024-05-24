package com.bugbender.ecommerce.catalog.repository

import com.bugbender.ecommerce.catalog.model.data.ProductCategoryEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ProductCategoryRepository : CrudRepository<ProductCategoryEntity, Long> {

    fun findByName(name: String): ProductCategoryEntity?

    @Query("SELECT DISTINCT c FROM ProductCategoryEntity c JOIN FETCH c.subCategories WHERE c.parentCategory IS NULL")
    fun findAllRootCategoriesWithSubCategories(): List<ProductCategoryEntity>

    @Query("SELECT c FROM ProductCategoryEntity c LEFT JOIN FETCH c.subCategories WHERE c.productCategoryId = :id")
    fun findByIdWithSubCategories(@Param("id") id: Long): ProductCategoryEntity?
}