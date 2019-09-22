package com.resta.panel.restraurant.DTO.baseInfo

import com.resta.core.restaurant.BussinessHour
import com.resta.core.restaurant.SpecialDate
import java.time.LocalDate

class SpecialDateDTO(
        var id: Int? = null,
        var date: LocalDate,
        var businessHour: BussinessHour
) {
    companion object {
        fun fromSpecialDate(specialDate: SpecialDate) =
                SpecialDateDTO(
                        id = specialDate.id,
                        date = specialDate.date,
                        businessHour = specialDate.businessHour
                )
    }
}
