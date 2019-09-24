package com.resta.auth

import com.resta.user.domain.AuthProvider
import org.springframework.security.core.AuthenticationException

object OAuth2UserInfoFactory {

    fun getOAuth2UserInfo(registrationId: String, attributes: Any): OAuth2UserInfo {
        return if (registrationId.equals(AuthProvider.facebook.toString(), ignoreCase = true)) {
            FacebookOAuth2UserInfo(attributes as Map<String, Any>)
        } else if (registrationId.equals(AuthProvider.facebook.toString(), ignoreCase = true)) {
            FacebookOAuth2UserInfo(attributes as Map<String, Any>)
        } else if (registrationId.equals(AuthProvider.github.toString(), ignoreCase = true)) {
            FacebookOAuth2UserInfo(attributes as Map<String, Any>)
        } else {
            throw OAuth2AuthenticationProcessingException("Sorry! Login with $registrationId is not supported yet.")
        }
    }
}

class OAuth2AuthenticationProcessingException : AuthenticationException {
    constructor(msg: String, t: Throwable) : super(msg, t) {}

    constructor(msg: String) : super(msg) {}
}
