package com.bugbender.ecommerce.catalog.model.data

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "attribute_option")
data class AttributeOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_option_id")
    val attributeOptionId: Long = 0L,
    @Column(name = "name")
    var name: String = "",

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "attribute_type_id")
    val attributeType: AttributeTypeEntity? = null
)