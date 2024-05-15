package com.bugbender.ecommerce.users.model.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "role")
data class RoleEntity(
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    var roleId: Long = 0L,
    @Column(name = "name", length = 20)
    var name: String = "",

    @ManyToMany(mappedBy = "roles")
    var users: List<UserEntity> = listOf(),

    @ManyToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_authority",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "authority_id")]
    )
    var authorities: List<AuthorityEntity> = listOf()
) : Serializable