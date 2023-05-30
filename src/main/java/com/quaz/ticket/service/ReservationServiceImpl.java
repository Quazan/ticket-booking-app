package com.quaz.ticket.service;

import com.quaz.ticket.entity.Reservation;
import com.quaz.ticket.entity.ScreeningSeat;
import com.quaz.ticket.repository.ReservationRepository;
import com.quaz.ticket.repository.ScreeningSeatRepository;
import static java.time.temporal.ChronoUnit.MINUTES;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningSeatRepository screeningSeatRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        //TODO validate that all seats are not reserved
        shouldBeMadeForOneScreening(reservation);
        shouldBeMadeBeforeScreening(reservation);
        final var persistedReservation =  reservationRepository.save(reservation);
        verifyThatNoEmptyPlacesInRowsAreLeft(persistedReservation);
        return persistedReservation;
    }

    private void verifyThatNoEmptyPlacesInRowsAreLeft(Reservation reservation) {
        final var screening = reservation.getScreening();
        final var rowToScreeningSeats = screeningSeatRepository.findByScreening_Id(screening.getId()).stream()
                .collect(Collectors.groupingBy(screeningSeat -> screeningSeat.getSeat().getRow()));

        rowToScreeningSeats.forEach((row, screeningSeats) -> {
            screeningSeats.sort(Comparator.comparingInt(screeningSeat -> screeningSeat.getSeat().getNumber()));
            var foundReserved = false;
            var foundEndOfReserved = false;
            for (var screeningSeat : screeningSeats) {
                if (!screeningSeat.isAvailable()) {
                    foundReserved = true;
                } else if (foundReserved) {
                    foundEndOfReserved = true;
                }
                if (!screeningSeat.isAvailable() && foundEndOfReserved) {
                    throw new IllegalArgumentException("Reservation should not leave empty places in rows");
                }
            }
        });
    }

    private void shouldBeMadeForOneScreening(Reservation reservation) {
        for (ScreeningSeat seat : reservation.getReservedSeats()) {
            if (!Objects.equals(seat.getScreening(), reservation.getScreening())) {
                throw new IllegalArgumentException("Reservation should be made for one screening");
            }
        }
    }

    private void shouldBeMadeBeforeScreening(Reservation reservation) {
        final var screening = reservation.getScreening();

        if (reservation.getReservationTime().plus(15, MINUTES).isAfter(screening.getScreeningTime())) {
            throw new IllegalArgumentException("Reservation should be made at least 15 minutes before screening time");
        }
    }

}
