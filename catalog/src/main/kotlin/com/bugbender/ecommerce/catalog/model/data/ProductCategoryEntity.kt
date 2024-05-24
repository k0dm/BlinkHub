package com.bugbender.ecommerce.catalog.model.data

import jakarta.persistence.*
import java.io.Serializable


@Entity
@Table(name = "product_category")
data class ProductCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    val productCategoryId: Long = 0L,

    @Column(name = "name", length = 255, nullable = false)
    val name: String = "",

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "parent_id")
    var parentCategory: ProductCategoryEntity? = null,

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var subCategories: MutableList<ProductCategoryEntity> = mutableListOf(),
) : Serializable {

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$productCategoryId)"
    }
}