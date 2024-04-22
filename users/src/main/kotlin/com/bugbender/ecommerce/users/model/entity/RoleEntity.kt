package com.bugbender.ecommerce.users.model.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "roles")
data class RoleEntity(
    @Id
    @GeneratedValue
    var id: Long = 0L,
    @Column(name = "name", length = 10)
    var name: String = "",

    @ManyToMany(mappedBy = "roles")
    var users: List<UserEntity> = listOf(),

    @ManyToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_authorities",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "id")]
    )
    var authorities: List<AuthorityEntity> = listOf()
) : Serializable