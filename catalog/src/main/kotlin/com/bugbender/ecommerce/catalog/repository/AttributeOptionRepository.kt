package com.bugbender.ecommerce.catalog.repository

import com.bugbender.ecommerce.catalog.model.data.AttributeOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AttributeOptionRepository : JpaRepository<AttributeOptionEntity, Long> {

   // fun findByAttributeType(attributeTypeId: Long): List<AttributeOptionEntity>
}