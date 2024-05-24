package com.bugbender.ecommerce.catalog.model.data

import jakarta.persistence.*

@Entity
@Table(name = "product_status")
data class ProductStatusEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id")
    var statusId: Long = 0L,

    @Column(name = "name", nullable = false)
    val name: String,

    @OneToMany(mappedBy = "status", cascade = [CascadeType.ALL], orphanRemoval = true)
    var products: List<ProductEntity> = listOf()
)