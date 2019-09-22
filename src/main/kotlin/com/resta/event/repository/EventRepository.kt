package com.resta.event.repository

import com.resta.event.domain.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long>