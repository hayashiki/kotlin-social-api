package com.resta.core.restaurant

import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class BussinessHour(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var openTime: LocalTime = LocalTime.of(8, 0),
        var closeTime: LocalTime = LocalTime.of(20, 0),
        var isClosed: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + openTime.hashCode()
        return result
    }
}
