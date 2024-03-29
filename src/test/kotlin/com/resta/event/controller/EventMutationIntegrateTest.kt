package com.resta.event.controller

import com.resta.BaseIntegrateTest
import com.resta.event.domain.EventDTO
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class EventMutationIntegrateTest : BaseIntegrateTest() {

    @Test
    @WithUserDetails(value= BaseIntegrateTest.CREATED_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailService")
    @Throws(Exception::class)
    fun createEventTest__normal() {

        val event = EventDTO(
                "hello",
                "hello description"
        )

        val perform = mockMvc
                .perform(
                        post("/api/events")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(event))
                )

        perform
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("id").isNumber)
                .andExpect(jsonPath("owner.name").value(this.createdUser.name!!))

    }
}