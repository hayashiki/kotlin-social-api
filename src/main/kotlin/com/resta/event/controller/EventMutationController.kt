package com.resta.event.controller

import com.resta.auth.CurrentUser
import com.resta.event.domain.EventDTO
import com.resta.event.service.EventCreateSvc
import com.resta.user.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping(value = ["/api/events"])
class EventMutationController(
    private val eventCreateSvc: EventCreateSvc
) {
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    fun createEvent(
            @CurrentUser user: User,
            @RequestBody @Valid eventDTO: EventDTO,
            errors: Errors
    ): ResponseEntity<*> {
        val event = eventCreateSvc.create(eventDTO, user)
        return ResponseEntity.ok(event)
    }
}