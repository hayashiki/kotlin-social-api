package com.resta.user.controller

import com.resta.auth.CurrentUser
import com.resta.user.domain.User
import com.resta.user.domain.UserTokenView
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = ["/api/users"])
class UserController() {

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    fun getUsersMe(@CurrentUser user: User): ResponseEntity<*> {
        return buildResponse(user)
    }

    private fun buildResponse(user: User): ResponseEntity<*> {
        val userResource = UserTokenView(user)
        return ResponseEntity.ok(userResource)
    }
}