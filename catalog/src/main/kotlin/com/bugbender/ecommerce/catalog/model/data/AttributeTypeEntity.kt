package com.bugbender.ecommerce.catalog.model.data

import jakarta.persistence.*

@Entity
@Table(name = "attribute_type")
data class AttributeTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_type_id")
    val attributeTypeId: Long = 0L,
    @Column(name = "name")
    var name: String = "",

    @OneToMany(mappedBy = "attributeType", cascade = [CascadeType.ALL])
    val attributeOptions: MutableList<AttributeOptionEntity> = mutableListOf()
)