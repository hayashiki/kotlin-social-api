package com.resta.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.hateoas.Link


open class BaseController {

    protected fun BadRequest(errors: Errors): ResponseEntity<*> {
        return ResponseEntity.badRequest().body(ErrorsResource(errors))
    }

    protected fun NotFound(): ResponseEntity<*> {
        return ResponseEntity.notFound().build<Any>()
    }

    protected fun forbidden(): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build<Any>()
    }
}

class ErrorsResource(content: Errors, vararg links: Link) {
    init {
    }
}
