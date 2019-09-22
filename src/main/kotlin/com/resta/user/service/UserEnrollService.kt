package com.resta.user.service

import com.resta.auth.AuthController
import com.resta.user.domain.User
import com.resta.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserEnrollService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
) {

    fun enrollByEmailPassword(
            email: String, password: String, name: String
    ) : User {
        val user = User(email, passwordEncoder.encode(password), name)

        logger.info("enrollByEmailPassword {}", user)
        logger.info("enrollByEmailPassword {}", user.email)
        logger.info("enrollByEmailPassword {}", user.name)
        logger.info("enrollByEmailPassword {}", user.roles)

        return userRepository.save(user)
    }

    companion object {

        private val logger = LoggerFactory.getLogger(UserEnrollService::class.java)
    }
}