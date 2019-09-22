package com.resta.panel.identity.DTO

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateRestaurantDTO (
        @field:NotBlank
        var name: String,

        @field:Email
        var email: String
)
