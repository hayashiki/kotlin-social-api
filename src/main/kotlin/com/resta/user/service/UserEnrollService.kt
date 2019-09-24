package com.resta.user.service

import com.resta.auth.AuthController
import com.resta.auth.FacebookOAuth2UserInfo
import com.resta.auth.FacebookUserInfoFetcher
import com.resta.user.domain.AuthProvider
import com.resta.user.domain.User
import com.resta.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserEnrollService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val facebookUserInfoFetcher: FacebookUserInfoFetcher
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

    fun byFacebook(accessToken: String): User {
        val userInfo = facebookUserInfoFetcher!!.getUserInfo(accessToken)

        return this.enrollByFacebook(userInfo)
    }

    private fun enrollByFacebook(
        userInfo: FacebookOAuth2UserInfo) : User {

        val email = userInfo.email
        val byEmail = userRepository!!.findByEmailAndProvider(email, AuthProvider.facebook)
        return byEmail.orElseGet { userRepository.save(User(userInfo, AuthProvider.facebook)) }

    }

    companion object {

        private val logger = LoggerFactory.getLogger(UserEnrollService::class.java)
    }
}