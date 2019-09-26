package com.resta.auth.oauth2

import com.resta.config.AppProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2AuthenticationSuccessHandler @Autowired
internal constructor(private val tokenProvider: TokenProvider, private val appProperties: AppProperties,
                     private val environment: Environment,
                     private val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository) : SimpleUrlAuthenticationSuccessHandler() {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val targetUrl = determineTargetUrl(request, response, authentication)

        if (response.isCommitted) {
            logger.debug("Response has already been committed. Unable to redirect to $targetUrl")
            return
        }

        clearAuthenticationAttributes(request, response)
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    protected fun determineTargetUrl(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication): String {
        val redirectUri = CookieUtils.getCookie(request, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map<String>({ it.value })

        if (redirectUri.isPresent && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication")
        }

        val targetUrl = redirectUri.orElse(defaultTargetUrl)

        val token = tokenProvider.createToken(authentication)

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString()
    }

    protected fun clearAuthenticationAttributes(request: HttpServletRequest, response: HttpServletResponse) {
        super.clearAuthenticationAttributes(request)
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response)
    }

    private fun isAuthorizedRedirectUri(uri: String): Boolean {
        return true
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException : RuntimeException {
    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
