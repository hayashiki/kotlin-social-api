package com.resta.auth

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Slf4j
@Service
class FacebookUserInfoFetcher(val facebookUserInfoRestTemplate: FacebookUserInfoRestTemplate) {

    fun getUserInfo(accessToken: String): FacebookOAuth2UserInfo {
        val client = facebookUserInfoRestTemplate.get()
        val attributes = client.getForObject("/me?access_token={access_token}&fields=id,first_name,middle_name,last_name,name,email,picture.width(250).height(250)", Map::class.java, accessToken)
        return OAuth2UserInfoFactory.getOAuth2UserInfo("facebook", attributes as Any) as FacebookOAuth2UserInfo
    }
}
