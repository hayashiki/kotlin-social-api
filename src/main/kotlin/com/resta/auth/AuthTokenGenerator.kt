package com.resta.auth

import com.resta.auth.oauth2.TokenProvider
import com.resta.user.domain.User
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthTokenGenerator(
        val tokenProvider: TokenProvider,
        val authenticationManager: AuthenticationManager
) {

    fun byEmailPassword(email: String, password: String): String {

        logger.info("byEmailPassword {}", email)
        logger.info("byEmailPassword {}", password)

        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(email, password)
        )

        logger.info("byEmailPassword {}", authentication)

        val token = tokenProvider.createToken(authentication)

        logger.info("byEmailPassword {}", token)

        return token
    }

    companion object {

        private val logger = LoggerFactory.getLogger(AuthTokenGenerator::class.java)
    }

//    fun byUser(user: User): String {
//        return tokenProvider.createToken(user)
//    }
}