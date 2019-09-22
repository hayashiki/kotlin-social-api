package com.resta.repository

import com.resta.core.address.CityRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

//@RunWith(SpringRunner::class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//class CityRepositoryTests {
//
//    @Autowired
//    lateinit var cityRepository: CityRepository
//
//    @Test
//    fun `findPartlyByNameOrAlias_ find by alias name = gw`() {
//        val result = cityRepository.findByNameIgnoreCase("aaa")
//        Assert.assertEquals(1, result)
//    }
//}
