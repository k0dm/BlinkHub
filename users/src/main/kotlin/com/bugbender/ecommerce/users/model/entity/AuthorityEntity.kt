package com.bugbender.ecommerce.users.model.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "authorities")
data class AuthorityEntity(
    @Id
    @GeneratedValue
    var id: Long = 0,
    @Column(name = "name", length = 10)
    var name: String = "",
    @ManyToMany(mappedBy = "authorities")
    var roles: List<RoleEntity> = listOf()
) : Serializable