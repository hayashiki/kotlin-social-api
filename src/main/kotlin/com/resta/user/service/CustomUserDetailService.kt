package com.resta.user.service

import com.resta.auth.oauth2.UserPrincipalAdapter
import com.resta.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class CustomUserDetailService(
        val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
                .orElseThrow { UsernameNotFoundException("User not found with email : $email") }

        return UserPrincipalAdapter.create(user)

    }

    fun loadUserById(id: Long?): UserDetails {
        var user = userRepository.findById(id!!).orElseThrow { ResourceNotFoundException("User", "id", id) }
        return UserPrincipalAdapter.create(user)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(val resourceName: String, val fieldName: String, val fieldValue: Any) : RuntimeException(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue))
