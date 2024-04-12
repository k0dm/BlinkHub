package com.bugbender.ecommerce.users.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue
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
    var encryptedPassword: String = ""
)