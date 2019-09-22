package com.resta.event.domain

import javax.validation.constraints.NotEmpty

data class EventDTO(
        @NotEmpty
        val name: String,

        @NotEmpty
        val description: String
) {
}