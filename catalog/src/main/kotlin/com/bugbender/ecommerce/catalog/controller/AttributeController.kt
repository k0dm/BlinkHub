package com.bugbender.ecommerce.catalog.controller

import com.bugbender.ecommerce.catalog.model.data.AttributeOptionEntity
import com.bugbender.ecommerce.catalog.model.data.AttributeTypeEntity
import com.bugbender.ecommerce.catalog.model.request.AttributeNameRequestModel
import com.bugbender.ecommerce.catalog.service.AttributeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/catalog/attributes")
class AttributeController(private val attributeService: AttributeService) {

    @GetMapping
    fun getAllAttributeTypes(): ResponseEntity<List<AttributeTypeEntity>> {
        val attributeTypes = attributeService.allAttributeTypes()
        return ResponseEntity.ok(attributeTypes)
    }

    @PostMapping
    fun createAttributeType(@RequestBody createAttributeTypeRequestModel: AttributeNameRequestModel) {
        attributeService.createAttributeType(createAttributeTypeRequestModel)
    }

    @GetMapping("/{id}")
    fun getAttributeTypeById(@PathVariable("id") id: Long): ResponseEntity<AttributeTypeEntity> {
        val attributeType = attributeService.attributeTypeById(id)
        return ResponseEntity.ok(attributeType)
    }

    @PutMapping("/{id}")
    fun updateAttributeTypeNameById(
        @PathVariable("id") id: Long,
        @RequestBody attributeNameRequestModel: AttributeNameRequestModel
    ): ResponseEntity<AttributeTypeEntity> {
        val updatedAttributeType = attributeService.updateAttributeTypeName(
            attributeTypeId = id,
            name = attributeNameRequestModel.name
        )
        return ResponseEntity.ok(updatedAttributeType)
    }

    @DeleteMapping("/{id}")
    fun deleteAttributeTypeById(@PathVariable("id") id: Long) {
        attributeService.deleteAttributeTypeById(id)
    }

    //options
    @GetMapping("/{id}/options")
    fun getAttributeOptions(@PathVariable("id") id: Long): ResponseEntity<List<AttributeOptionEntity>> {
        val attributeOptions = attributeService.attributeOptions(id)
        return ResponseEntity.ok(attributeOptions)
    }

    @PostMapping("/{id}/options")
    fun createAttributeOption(
        @PathVariable("id") id: Long,
        @RequestBody attributeNameRequestModel: AttributeNameRequestModel
    ) {
        attributeService.createAttributeOption(attributeTypeId = id, name = attributeNameRequestModel.name)
    }

    @PutMapping("/options/{id}")
    fun updateAttributeOptionNameById(
        @PathVariable("id") id: Long,
        @RequestBody attributeNameRequestModel: AttributeNameRequestModel
    ): ResponseEntity<AttributeOptionEntity> {
        val updatedAttributeOption = attributeService.updateAttributeOptionName(
            attributeTypeId = id,
            name = attributeNameRequestModel.name
        )
        return ResponseEntity.ok(updatedAttributeOption)
    }

    @DeleteMapping("/options/{id}")
    fun deleteAttributeOptionDeleteById(@PathVariable("id") id: Long) {
        attributeService.deleteAttributeOptionById(id)
    }
}