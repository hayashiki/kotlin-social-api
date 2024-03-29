package com.resta.user.repository

import com.resta.user.domain.AuthProvider
import com.resta.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>

    fun findByEmailAndProvider(email: String, provider: AuthProvider): Optional<User>
}