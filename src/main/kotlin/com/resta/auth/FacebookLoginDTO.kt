package com.resta.auth

import javax.validation.constraints.NotEmpty

class FacebookLoginDTO {
    @NotEmpty

    lateinit var facebookAccessToken: String
}