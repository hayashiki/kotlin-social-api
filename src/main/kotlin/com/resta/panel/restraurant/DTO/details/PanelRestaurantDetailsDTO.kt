package com.resta.panel.restraurant.DTO.details

import com.resta.core.restaurant.Restaurant
import com.resta.panel.restraurant.DTO.baseInfo.AddressDTO

data class PanelRestaurantDetailsDTO(
        var name: String,
        var urlName: String,
        var description: String,
        var email: String,
        var phoneNumber: String,
        var type: Restaurant.RestaurantType,
        var address: AddressDTO
) {
    companion object {
//        fun fromRestaurant(restaurant: Restaurant) =
//                PanelRestaurantDetailsDTO(
//                        name = restaurant.name,
//                        email = restaurant.email,
//                        address = AddressDTO.fromAddress(restaurant.address)
//                )

    }
}
