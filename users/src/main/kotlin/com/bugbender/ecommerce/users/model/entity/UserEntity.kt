package com.bugbender.ecommerce.users.model.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "`user`")
data class UserEntity(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0L,
    @Column(name = "first_name", length = 50, nullable = false)
    var firstName: String = "",
    @Column(name = "last_name", length = 50, nullable = false)
    var lastName: String = "",
    @Column(name = "email", length = 120, nullable = false, unique = true)
    var email: String = "",
    @Column(name = "user_id", nullable = false, unique = true)
    var userId: String = "",
    @Column(name = "encrypted_password", nullable = false, unique = true)
    var encryptedPassword: String = "",

    @ManyToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")]
    )
    var roles: List<RoleEntity> = listOf()
): Serializable