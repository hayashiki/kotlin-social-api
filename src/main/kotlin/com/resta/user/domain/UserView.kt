package com.resta.user.domain

data class UserView(
        val id: Long?,
        val name: String,
        val imageUrl: String,
        val providers: MutableSet<AuthProvider>
)

const val DEFAULT_PROFILE = "https://ui-avatars.com/api/?length=1";

fun User.view() = UserView(
        id = id,
        name = name.orEmpty(),
        imageUrl = imageUrl ?: DEFAULT_PROFILE,
        providers = hashSetOf(provider!!)
)