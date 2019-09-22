package com.resta.core.restaurant

import org.springframework.stereotype.Service
import java.util.*

@Service
class ReservationService(
        private val restaurantRepository: RestaurantRepository
){
    fun findById(id: Int): Optional<Restaurant> = restaurantRepository.findById(id)
    fun save(restaurant: Restaurant) = restaurantRepository.save(restaurant)
}


//@Service
//class ReservationService(){
//    private val authorizationService: AuhtorizationService,
//    private val reservationService: ReservationService
//} (
//    fun addReservation() {
//        reservationService.validateReservationTime()
//        val reservationSpots = reservationService.findReservationSpots(createReservationDTO.spots, restratunt)
//        val reservation = reservationService.save(
//                Reservation(
//                        panelCreateReservationDTO = createReservationDTO,
//                        restaurant = restaurant,
//                        approvedBy = authorizationService.getCurrentUser(),
//                        customer = prepareSeservationCustomer(createReservationDTO, restaurant),
//                        sports = reservationSpots
//                )
//        )
//        restaurant.reservations.add(reservation)
//}
//)