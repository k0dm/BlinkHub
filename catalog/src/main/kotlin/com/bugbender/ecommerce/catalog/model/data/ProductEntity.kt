package com.bugbender.ecommerce.catalog.model.data

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    var productId: Long = 0L,
    @Column(name = "name", length = 100, nullable = false)
    val name: String,
    @Column(name = "description", length = 10000, nullable = false)
    val description: String,
    @Column(name = "sku", length = 25, nullable = false)
    val sku: String,
    @Column(name = "original_price", nullable = false)
    val originalPrice: Double,
    @Column(name = "sale_price", nullable = false)
    val salePrice: Double,
    @Column(name = "qty", nullable = false)
    val qty: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    val status: ProductStatusEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    val category: ProductCategoryEntity,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: List<ProductImageEntity> = listOf()
)


