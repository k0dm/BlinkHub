package com.bugbender.ecommerce.users.service

import com.bugbender.ecommerce.users.data.UserRepository
import com.bugbender.ecommerce.users.model.entity.UserEntity
import com.bugbender.ecommerce.users.model.shared.UserDto
import org.springframework.stereotype.Service
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies

interface UserService {

    fun createUser(userDto: UserDto): UserDto

    @Service
    class Base(private val repository: UserRepository) : UserService {

        private val modelMapper: ModelMapper = ModelMapper()

        init {
            modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        }

        override fun createUser(userDto: UserDto): UserDto {
            val userEntity = modelMapper.map(userDto, UserEntity::class.java)
            repository.save(userEntity)
            return userDto
        }
    }
}