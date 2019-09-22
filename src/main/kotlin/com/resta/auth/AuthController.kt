package com.resta.auth

import com.resta.auth.email.LoginRequestDTO
import com.resta.auth.email.SignUpRequestDTO
import com.resta.controller.BaseController
import com.resta.user.domain.UserTokenView
import com.resta.user.repository.UserRepository
import com.resta.user.service.UserEnrollService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val authTokenGenerator: AuthTokenGenerator,
    private val userEnrollService: UserEnrollService
) : BaseController() {

//    @PostMapping("/login/facebook")
//    fun facebookLogin(@RequestBody @Valid dto: FacebookLoginDTO,
//                      errors: Errors
//    ) : ResponseEntity<*> {
//        if (errors.hasErrors()) {
//            return BadRequest(errors)
//        }
//
//        val user = UserEnrollService.byFacebook(dto.facebookAccessToken)
//        val token = authTokenGenerator.byUser(user)
//        val userResource = UserTokenView(user, token)
//
//        return ResponseEntity.ok(userResource)
//    }

    @PostMapping("/login/email")
    fun authenticateUser(
            @Valid @RequestBody loginRequestDTO: LoginRequestDTO,
            errors: Errors
    ) : ResponseEntity<*> {
        if (errors.hasErrors()) {
            return BadRequest(errors)
        }

        val byEmail = userRepository.findByEmail(loginRequestDTO.email)

        if (!byEmail.isPresent) {
            return ResponseEntity.notFound().build<Any>()
        }

        val token = authTokenGenerator.byEmailPassword(loginRequestDTO.email, loginRequestDTO.password)
        val userResource = UserTokenView(byEmail.get(), token)
        return ResponseEntity.ok(userResource)
    }

    @PostMapping("/signup/email")
    fun registerUser(
            @Valid @RequestBody signUpRequestDTO: SignUpRequestDTO,
            errors: Errors
    ): ResponseEntity<*> {

        logger.info("start {}", signUpRequestDTO)
        val user = userEnrollService.enrollByEmailPassword(
                signUpRequestDTO.email,
                signUpRequestDTO.password,
                signUpRequestDTO.name
        )

        logger.info("start2 {}", user)
        val token = authTokenGenerator.byEmailPassword(
                signUpRequestDTO.email,
                signUpRequestDTO.password
        )

        logger.info("user info {}", user)
        logger.info("user info {}", token)
        val userResource = UserTokenView(user, token)

        return ResponseEntity.ok(userResource)
    }

    companion object {

        private val logger = LoggerFactory.getLogger(AuthController::class.java)
    }
}