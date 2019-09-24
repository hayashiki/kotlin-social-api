package com.resta.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class FacebookUserInfoRestTemplate {

    @Autowired
    lateinit var restTemplateBuilder: RestTemplateBuilder

    @Autowired
    lateinit var config: FacebookConfig

    @Bean
    fun get(): RestTemplate {

        return restTemplateBuilder
                .rootUri(config.graphUrl)
                .build()
    }
}