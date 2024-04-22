package com.bugbender.ecommerce.users.data

import com.bugbender.ecommerce.users.model.entity.RoleEntity
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<RoleEntity, Long> {

    fun findByName(name: String): RoleEntity?
}