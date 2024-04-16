package com.bugbender.ecommerce.users.service

import com.bugbender.ecommerce.users.data.CatalogServiceClient
import com.bugbender.ecommerce.users.data.UserRepository
import com.bugbender.ecommerce.users.model.entity.UserEntity
import com.bugbender.ecommerce.users.model.shared.UserDto
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

interface UserService : UserDetailsService {

    fun createUser(userDto: UserDto): UserDto

    fun getUserDetailsByUsername(email: String): UserDto

    fun getUserByUserId(userId: String): UserDto

    @Service
    class Base(
        private val repository: UserRepository,
        private val encoder: BCryptPasswordEncoder,
        private val catalogServiceClient: CatalogServiceClient,
    ) : UserService {

        private val modelMapper: ModelMapper = ModelMapper()
        private val logger = LoggerFactory.getLogger(this::class.java)

        init {
            modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        }

        override fun createUser(userDto: UserDto): UserDto {
            userDto.encryptedPassword = encoder.encode(userDto.password)
            val userEntity = modelMapper.map(userDto, UserEntity::class.java)
            repository.save(userEntity)
            return userDto
        }

        override fun getUserDetailsByUsername(email: String): UserDto {
            val userEntity = repository.findByEmail(email) ?: throw UsernameNotFoundException(email)
            return modelMapper.map(userEntity, UserDto::class.java)
        }

        override fun getUserByUserId(userId: String): UserDto {
            val userEntity =
                repository.findByUserId(userId) ?: throw UsernameNotFoundException("No user with such id: $userId")


            val productListResponse = catalogServiceClient.getProducts(userId)
//            val productListResponse = try {
//                catalogServiceClient.getProducts(userId)
//            } catch (e: Exception) {
//                logger.error(e.message)
//                emptyList()
//            }

            val userDto = modelMapper.map(userEntity, UserDto::class.java)
            userDto.products = productListResponse
            return userDto
        }

        override fun loadUserByUsername(username: String): UserDetails {
            val userEntity = repository.findByEmail(username) ?: throw UsernameNotFoundException(username)
            val enable = true //todo make functionality for email verification
            return User(
                userEntity.email,
                userEntity.encryptedPassword,
                enable,
                true,
                true,
                true,
                emptyList()
            )
        }
    }
}