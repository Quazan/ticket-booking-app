package com.quaz.ticket.reservation;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface ReservationService {

    @Transactional
    Reservation createReservation(Reservation reservation);

    Optional<Reservation> getReservation(Long id);

}
