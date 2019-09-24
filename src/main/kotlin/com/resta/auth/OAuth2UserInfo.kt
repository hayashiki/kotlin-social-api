package com.resta.auth

abstract class OAuth2UserInfo(attributes: Map<String, Any>) {
    var attributes: Map<String, Any>
        protected set

    abstract val id: String

    abstract val name: String

    abstract val email: String

    abstract val imageUrl: String

    init {
        this.attributes = attributes
    }
}
