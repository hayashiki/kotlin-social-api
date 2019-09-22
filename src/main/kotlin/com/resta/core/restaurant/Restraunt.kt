package com.resta.core.restaurant

import com.resta.core.address.Address
import org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator
import java.time.Duration
import javax.persistence.*

@Entity
data class Restaurant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var name: String,
//        var urlName: String,
//        var description: String = "",
//        var phoneNumner: String,
        var email: String,
//        var avgReservationTime: Duration = Duration.ofHours(1),
//        var rate: Float = 0f,
//        var servive_rate: Float = 0f,
//        var food_rate: Float = 0f,
//        var price_quality_rate: Float = 0f,
//        var isActive: Boolean = false,

//        @Enumerated(EnumType.STRING)
//        var type: RestaurantType,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var address: Address = Address()
) {
    enum class RestaurantType {
        RESTRAUNT,
        BAR
    }
}