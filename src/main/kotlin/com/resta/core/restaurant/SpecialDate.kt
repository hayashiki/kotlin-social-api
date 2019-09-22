package com.resta.core.restaurant

import java.time.LocalDate
import javax.persistence.*

@Entity
class SpecialDate(
        @Id
        @GeneratedValue
        var id: Int? = null,
        var date: LocalDate,
        var description: String? = null,

        @OneToOne
        var businessHour: BussinessHour,
        @ManyToOne
        var restaurant: Restaurant
)
