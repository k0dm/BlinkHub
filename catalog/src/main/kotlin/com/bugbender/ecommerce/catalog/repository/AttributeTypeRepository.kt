package com.bugbender.ecommerce.catalog.repository

import com.bugbender.ecommerce.catalog.model.data.AttributeTypeEntity
import org.springframework.data.repository.CrudRepository

interface AttributeTypeRepository : CrudRepository<AttributeTypeEntity, Long> {

    fun findByName(name: String): AttributeTypeEntity?
}