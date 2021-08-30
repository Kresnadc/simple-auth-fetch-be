package com.example.simpleauth.services

import com.example.simpleauth.configuration.UserProp
import com.example.simpleauth.model.dto.UserRegistRequest
import com.example.simpleauth.model.dto.UserRegistResponse
import com.example.simpleauth.model.exception.UserAlreadyExistsException
import com.example.simpleauth.model.dao.User
import com.example.simpleauth.repository.UserRepository
import mu.KLogger
import mu.KotlinLogging
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val userProp: UserProp,
    private val logger: KLogger = KotlinLogging.logger {}
) : UserService {

    override fun register(registRequest: UserRegistRequest): UserRegistResponse {
        var user = userRepository.findOneByName(registRequest.name)

        if (user != null) {
            logger.error("User name: ${user.name} is exists!")
            throw UserAlreadyExistsException("User name: ${user.name} is exists!")
        }

        user = User(
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            registRequest.name,
            registRequest.phone,
            registRequest.role,
            RandomStringUtils.randomAlphanumeric(4),
        )

        val save = userRepository.save(user)

        return UserRegistResponse(save.password)
    }
}