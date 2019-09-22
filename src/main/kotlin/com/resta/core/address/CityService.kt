package com.resta.core.address

import org.springframework.stereotype.Service

@Service
class CityService(private val cityRepository: CityRepository) {
    fun findByNameIgnoreCase(name: String): City? =
            cityRepository.findByNameIgnoreCase(name)
}
