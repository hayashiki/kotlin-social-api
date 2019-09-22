package com.resta.panel.restraurant.DTO.baseInfo

import com.resta.core.restaurant.Restaurant
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class BaseInfoDTO (
    var setttigs: BaseInfoSettingsDTO,

    @field:NotBlank
    var name: String,

    @field:Size(max = 100, message = "Email too long.")
    @field:NotBlank
    @field:Email
    var email: String,

    @field:Valid
    var address: AddressDTO,

    var specialDates: MutableList<SpecialDateDTO>

) {
    companion object {
        private fun updateAddress(restaurant: Restaurant, baseInfoDTO: BaseInfoDTO) {
            restaurant.address.streetName = baseInfoDTO.address.streetName
            restaurant.address.buildingNumber = baseInfoDTO.address.buildingNumber
            restaurant.address.postalCode = baseInfoDTO.address.postalCode
            restaurant.address.latitude = baseInfoDTO.address.latitude
            restaurant.address.longitude = baseInfoDTO.address.longitude
        }
    }
}