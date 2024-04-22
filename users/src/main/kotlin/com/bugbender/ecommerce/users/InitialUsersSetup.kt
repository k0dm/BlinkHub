package com.bugbender.ecommerce.users

import com.bugbender.ecommerce.users.data.AuthorityRepository
import com.bugbender.ecommerce.users.data.RoleRepository
import com.bugbender.ecommerce.users.data.UserRepository
import com.bugbender.ecommerce.users.model.entity.AuthorityEntity
import com.bugbender.ecommerce.users.model.entity.RoleEntity
import com.bugbender.ecommerce.users.model.entity.UserEntity
import com.bugbender.ecommerce.users.model.shared.Roles
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class InitialUsersSetup {

    @Autowired
    private lateinit var authorityRepository: AuthorityRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @EventListener
    @Transactional
    fun onApplicationEvent(event: ApplicationReadyEvent) {

        val read = createAuthority("READ")
        val write = createAuthority("WRITE")
        val delete = createAuthority("DELETE")

        createRole(name = Roles.ROLE_USER.name, authorities = listOf(read, write))
        val adminRole = createRole(name = Roles.ROLE_ADMIN.name, authorities = listOf(read, write, delete))

        userRepository.findByEmail(email = "k0dmairsoft@gmail.com") ?: userRepository.save(
            UserEntity(
                firstName = "SUPER",
                lastName = "ADMIN",
                email = "k0dmairsoft@gmail.com",
                userId = UUID.randomUUID().toString(),
                encryptedPassword = bCryptPasswordEncoder.encode("k0dmairsoft@gmail.com"),
                roles = listOf(adminRole)
            )
        )
    }

    @Transactional
    private fun createAuthority(name: String): AuthorityEntity =
        authorityRepository.findByName(name) ?: authorityRepository.save(AuthorityEntity(name = name))

    @Transactional
    private fun createRole(name: String, authorities: List<AuthorityEntity>) =
        roleRepository.findByName(name) ?: roleRepository.save(RoleEntity(name = name, authorities = authorities))
}