package com.resta.panel.identity

import com.resta.core.address.Address
import com.resta.core.address.CityService
import com.resta.core.restaurant.Restaurant
import com.resta.panel.identity.DTO.CreateRestaurantDTO
import org.springframework.stereotype.Service

@Service
class IdentityService(
        private val cityService: CityService
) {

    fun createRestaurant(createRestaurantDTO: CreateRestaurantDTO) {
        var restaurant = Restaurant(
                name = createRestaurantDTO.name,
                email = createRestaurantDTO.email,
                address = Address(
                    latitude = 52.402675f,
                    longitude = 16.923123f,
                    city = cityService.findByNameIgnoreCase("poznań")
                )
        )

    }
}