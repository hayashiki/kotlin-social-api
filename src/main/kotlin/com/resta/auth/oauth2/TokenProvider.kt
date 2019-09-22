package com.resta.auth.oauth2

import com.resta.config.AppProperties
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*
import io.jsonwebtoken.*

@Service
class TokenProvider(val appProperties: AppProperties) {
    fun createToken(authentication: Authentication): String {
        val userPrincipalAdapter = authentication.principal as UserPrincipalAdapter
        return token(userPrincipalAdapter)
    }

    private fun token(userPrincipalAdapter: UserPrincipalAdapter): String {
        val now = Date()
        val expiryDate = Date(now.time + appProperties.auth.tokenExpirationMsec )

        return Jwts.builder()
                .setSubject((userPrincipalAdapter.id!!).toString())
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.auth.tokenSecret)
                .compact()
    }
}