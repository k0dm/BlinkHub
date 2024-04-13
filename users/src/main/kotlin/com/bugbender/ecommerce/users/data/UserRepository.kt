package com.bugbender.ecommerce.users.data

import com.bugbender.ecommerce.users.model.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {

    fun findByEmail(email: String): UserEntity?
}