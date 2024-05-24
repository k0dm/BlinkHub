package com.bugbender.ecommerce.catalog.service

import com.bugbender.ecommerce.catalog.model.data.AttributeOptionEntity
import com.bugbender.ecommerce.catalog.model.data.AttributeTypeEntity
import com.bugbender.ecommerce.catalog.model.request.AttributeNameRequestModel
import com.bugbender.ecommerce.catalog.repository.AttributeOptionRepository
import com.bugbender.ecommerce.catalog.repository.AttributeTypeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

interface AttributeService {

    fun createAttributeType(model: AttributeNameRequestModel)

    fun allAttributeTypes(): List<AttributeTypeEntity>

    fun attributeTypeById(id: Long): AttributeTypeEntity

    fun updateAttributeTypeName(attributeTypeId: Long, name: String): AttributeTypeEntity

    fun deleteAttributeTypeById(id: Long)

    fun createAttributeOption(attributeTypeId: Long, name: String)

    fun attributeOptions(id: Long): List<AttributeOptionEntity>

    fun updateAttributeOptionName(attributeTypeId: Long, name: String): AttributeOptionEntity

    fun deleteAttributeOptionById(id: Long)

    @Service
    class Base(
        private val attributeTypeRepository: AttributeTypeRepository,
        private val attributeOptionRepository: AttributeOptionRepository,
    ) : AttributeService {

        override fun createAttributeType(model: AttributeNameRequestModel) {
            attributeTypeRepository.save(AttributeTypeEntity(name = model.name))
        }

        override fun allAttributeTypes(): List<AttributeTypeEntity> =
            attributeTypeRepository.findAll().toList()

        override fun attributeTypeById(id: Long): AttributeTypeEntity =
            attributeTypeRepository.findById(id).get()

        @Transactional
        override fun updateAttributeTypeName(attributeTypeId: Long, name: String): AttributeTypeEntity {
            val attributeType = attributeTypeRepository.findById(attributeTypeId)
                .orElseThrow { RuntimeException("No attribute type with such id") }

            attributeType.name = name
            return attributeTypeRepository.save(attributeType)        }

        override fun deleteAttributeTypeById(id: Long) {
            attributeTypeRepository.deleteById(id)
        }

        @Transactional
        override fun createAttributeOption(attributeTypeId: Long, name: String) {
            val attributeType = attributeTypeRepository.findById(attributeTypeId)
                .orElseThrow { RuntimeException("No attribute type with such id") }

            attributeOptionRepository.save(AttributeOptionEntity(name = name, attributeType = attributeType))
        }

        override fun attributeOptions(id: Long): List<AttributeOptionEntity> =
            attributeTypeById(id).attributeOptions

        @Transactional
        override fun updateAttributeOptionName(attributeTypeId: Long, name: String): AttributeOptionEntity {
            val attributeOption = attributeOptionRepository.findById(attributeTypeId)
                .orElseThrow { RuntimeException("No attribute type with such id") }

            attributeOption.name = name
            return attributeOptionRepository.save(attributeOption)        }

        override fun deleteAttributeOptionById(id: Long) {
            attributeOptionRepository.deleteById(id)
        }
    }
}