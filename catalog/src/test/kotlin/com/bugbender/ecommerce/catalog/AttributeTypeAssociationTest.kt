package com.bugbender.ecommerce.catalog

import com.bugbender.ecommerce.catalog.model.data.AttributeOptionEntity
import com.bugbender.ecommerce.catalog.model.data.AttributeTypeEntity
import com.bugbender.ecommerce.catalog.repository.AttributeTypeRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class AttributeTypeAssociationTest {

    @Autowired
    private lateinit var repository: AttributeTypeRepository

    @Test
    fun createAttributeType() {

        repository.save(
            AttributeTypeEntity(
                name = "Size",
                attributeOptions = mutableListOf(
                    AttributeOptionEntity(name = "S"),
                    AttributeOptionEntity(name = "M"),
                    AttributeOptionEntity(name = "L"),
                    AttributeOptionEntity(name = "XL")
                )
            )
        )

        val dbAttributeType = repository.findByName("Size")
        Assertions.assertNotNull(dbAttributeType)
        Assertions.assertTrue(dbAttributeType!!.attributeOptions.size == 4)
    }
}