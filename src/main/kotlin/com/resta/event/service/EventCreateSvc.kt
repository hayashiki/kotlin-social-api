package com.resta.event.service

import com.resta.event.domain.Event
import com.resta.event.domain.EventDTO
import com.resta.event.repository.EventRepository
import com.resta.user.domain.User
import com.resta.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class EventCreateSvc(
        val eventRepository: EventRepository,
        val userRepository: UserRepository
) {

    fun create(eventDTO: EventDTO, user_: User): Event {
        val user: User = userRepository.findById(user_.id!!).get()

        val event = Event(eventDTO, user)
        return eventRepository.save(event)
    }
}