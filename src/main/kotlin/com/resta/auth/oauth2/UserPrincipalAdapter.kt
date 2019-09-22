package com.resta.auth.oauth2

import com.resta.user.domain.User
import com.resta.user.domain.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

class UserPrincipalAdapter(val user: User) : OAuth2User, UserDetails {
    val id: Long?
    val email: String?
    private val password: String?
    private val authorities: Collection<GrantedAuthority>
    private var attributes: Map<String, Any>? = null

    init {
        this.id = user.id
        this.email = user.email
        this.password = user.password
        this.authorities = authorities(user.roles)
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getAttributes(): Map<String, Any>? {
        return attributes
    }

    fun setAttributes(attributes: Map<String, Any>) {
        this.attributes = attributes
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getName(): String {
        return id.toString()
    }

    override fun getUsername(): String? {
        return email
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        private fun authorities(roles: Set<UserRole>): Collection<GrantedAuthority> {
            return roles.map { r -> SimpleGrantedAuthority("ROLE_" + r.name) }
        }

        fun create(user: User): UserPrincipalAdapter {

            return UserPrincipalAdapter(user)
        }
    }
}