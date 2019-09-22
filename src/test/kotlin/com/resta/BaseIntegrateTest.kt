package com.resta

import com.fasterxml.jackson.databind.ObjectMapper
import com.resta.auth.oauth2.TokenProvider
import com.resta.user.domain.User
import com.resta.user.repository.UserRepository
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.transaction.BeforeTransaction
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Ignore
@ActiveProfiles("test")
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "server.money-whip.com", uriPort = 443)
class BaseIntegrateTest {
    protected lateinit var createdUser: User

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var tokenProvider: TokenProvider

    @Autowired
    lateinit protected var userRepository: UserRepository

    @BeforeTransaction
    fun setUp() {
        this.createdUser = createUser(CREATED_USER_EMAIL)
    }

    fun setDown() {
        this.userRepository.delete(this.createdUser)
    }

    private fun createUser(email: String): User {
        val user = User(email, "1234", "local")
        return this.userRepository.save(user)
    }

    companion object {
        public const val CREATED_USER_EMAIL = "createdUser-me@gmail.com"
    }


}