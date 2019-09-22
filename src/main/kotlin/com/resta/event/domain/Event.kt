package com.resta.event.domain

import com.resta.user.domain.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Event {
    @Id
    @GeneratedValue
    var id:Long? = null
    var name: String? = null
    var description: String? = null

    @CreationTimestamp
    var createdAt: LocalDateTime? = null

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null

    @ManyToOne
    var owner: User

    constructor(eventDto: EventDTO, user: User) {
        this.name = eventDto.name
        this.description = eventDto.description
        this.owner = user
    }
}