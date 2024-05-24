package com.bugbender.ecommerce.catalog.model.data

import jakarta.persistence.*

@Entity
@Table(name = "product_image")
data class ProductImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_image_id")
    var productImageId: Long = 0L,
    @Column(name = "url", nullable = false)
    val url: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity
)

