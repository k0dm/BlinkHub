package com.bugbender.ecommerce.users.data

import com.bugbender.ecommerce.users.model.entity.AuthorityEntity
import org.springframework.data.repository.CrudRepository

interface AuthorityRepository : CrudRepository<AuthorityEntity, Long> {

    fun findByName(name: String): AuthorityEntity?
}