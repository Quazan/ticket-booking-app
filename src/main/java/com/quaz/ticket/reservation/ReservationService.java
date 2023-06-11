package com.quaz.ticket.reservation;

import java.util.Optional;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    Optional<Reservation> getReservation(Long id);

}
