package com.resta.auth.oauth2

import com.resta.config.AppProperties
import com.resta.user.domain.User
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory

@Service
class TokenProvider(val appProperties: AppProperties) {
    fun createToken(authentication: Authentication): String {
        logger.info("auth!! {}", authentication)
        logger.info("auth!! principal {}", authentication.principal)
        val userPrincipalAdapter = authentication.principal as UserPrincipalAdapter
        logger.info("after auth!! {}", authentication)
        return token(userPrincipalAdapter)
    }

    fun createToken(user: User): String {
        val userPrincipalAdapter = UserPrincipalAdapter.create(user)
        return token(userPrincipalAdapter)
    }

    private fun token(userPrincipalAdapter: UserPrincipalAdapter): String {
        val now = Date()
        logger.info("now!! {}", now)
        val expiryDate = Date(now.time + appProperties.auth.tokenExpirationMsec )
        logger.info("expiryDate!! {}", expiryDate)

        return Jwts.builder()
                .setSubject((userPrincipalAdapter.id!!).toString())
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.auth.tokenSecret)
                .compact()
    }

    internal fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(appProperties.auth.tokenSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }

        return false
    }

    internal fun getUserIdFromToken(token: String): Long? {
        val claims = Jwts.parser()
                .setSigningKey(appProperties.auth.tokenSecret)
                .parseClaimsJws(token)
                .body
        return java.lang.Long.parseLong(claims.subject)
    }

    companion object {

        private val logger = LoggerFactory.getLogger(TokenProvider::class.java)
    }
}