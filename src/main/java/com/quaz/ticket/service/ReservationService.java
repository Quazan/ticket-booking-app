package com.quaz.ticket.service;

import com.quaz.ticket.entity.Reservation;
import com.quaz.ticket.repository.ReservationRepository;
import static java.time.temporal.ChronoUnit.MINUTES;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        shouldBeMadeForOneScreening(reservation);
        shouldBeMadeBeforeScreening(reservation);
        return reservationRepository.save(reservation);
    }

    private void shouldBeMadeForOneScreening(Reservation reservation) {
        final var screeningsCount = reservation.getReservedSeats().stream()
                .map(seat -> seat.getScreening().getId())
                .distinct().count();

        if (screeningsCount > 1) {
            throw new IllegalArgumentException("Cannot create reservation with more than one screening");
        }
    }

    private void shouldBeMadeBeforeScreening(Reservation reservation) {
        final var screening = reservation.getReservedSeats().get(0).getScreening();

        if (reservation.getReservationTime().plus(15, MINUTES).isAfter(screening.getScreeningTime())) {
            throw new IllegalArgumentException("Reservation should be made at least 15 minutes before screening time");
        }
    }

}
