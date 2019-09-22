package com.resta.user.domain

data class UserView(
    val id: Long?,
    val name: String
)

fun User.view() = UserView(
        id = id,
        name = name.orEmpty()
)