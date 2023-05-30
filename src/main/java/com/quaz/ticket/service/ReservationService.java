package com.quaz.ticket.service;

import com.quaz.ticket.entity.Reservation;
import com.quaz.ticket.repository.ReservationRepository;
import com.quaz.ticket.repository.ScreeningSeatRepository;
import static java.time.temporal.ChronoUnit.MINUTES;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningSeatRepository screeningSeatRepository;

    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        shouldBeMadeForOneScreening(reservation);
        shouldBeMadeBeforeScreening(reservation);
        final var persistedReservation =  reservationRepository.save(reservation);
        verifyThatNoEmptyPlacesInRowsAreLeft(persistedReservation);
        return persistedReservation;
    }

    private void verifyThatNoEmptyPlacesInRowsAreLeft(Reservation reservation) {
        final var screening = reservation.getReservedSeats().get(0).getScreening();
        final var rowToScreeningSeats = screeningSeatRepository.findByScreening_Id(screening.getId()).stream()
                .collect(Collectors.groupingBy(screeningSeat -> screeningSeat.getSeat().getRow()));

        rowToScreeningSeats.forEach((row, screeningSeats) -> {
            screeningSeats.sort(Comparator.comparingInt(screeningSeat -> screeningSeat.getSeat().getNumber()));
            var foundReserved = false;
            var foundEndOfReserved = false;
            for (var screeningSeat : screeningSeats) {
                if (!screeningSeat.isAvailable()) {
                    foundReserved = true;
                } else if (screeningSeat.isAvailable() && foundReserved) {
                    foundEndOfReserved = true;
                } else if (!screeningSeat.isAvailable() && foundEndOfReserved) {
                    throw new IllegalArgumentException("Reservation should not leave empty places in rows");
                }
            }
        });
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
