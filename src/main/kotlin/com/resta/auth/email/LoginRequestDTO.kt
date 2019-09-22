package com.resta.auth.email

import javax.validation.constraints.Email

data class LoginRequestDTO (
        @Email
        val email: String,
        val password: String
)
