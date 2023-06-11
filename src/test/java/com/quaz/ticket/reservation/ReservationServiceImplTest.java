package com.quaz.ticket.reservation;

import com.quaz.ticket.configuration.TestConfig;
import com.quaz.ticket.screening.Screening;
import com.quaz.ticket.screeningroom.Seat;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import com.quaz.ticket.screeningseat.ScreeningSeatRepository;
import static java.time.ZoneOffset.UTC;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.time.Duration;
import java.util.List;

class ReservationServiceImplTest {

    private ReservationRepository reservationRepository;

    private ScreeningSeatRepository screeningSeatRepository;

    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        screeningSeatRepository = mock(ScreeningSeatRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository, screeningSeatRepository);
    }

    @Test
    void givenReservationWithAlreadyReservedSeatWhenCreateReservationThenExceptionIsThrown() {
        //given
        final var oldReservation = new Reservation();
        final var alreadyReservedScreeningSeat = new ScreeningSeat();
        alreadyReservedScreeningSeat.setReservation(oldReservation);

        final var newReservation = new Reservation();
        newReservation.setReservedSeats(List.of(new ScreeningSeat(), alreadyReservedScreeningSeat));

        //when
        final var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.createReservation(newReservation)
        );


        //then
        Assertions.assertEquals("Reservation should only be made for available seats", thrown.getMessage());
    }

    @Test
    void givenReservationWithSeatFromAnotherScreeningWhenCreateReservationThenExceptionIsThrown() {
        //given
        final var screening = new Screening();
        screening.setId(1L);
        final var screeningSeat = new ScreeningSeat();
        screeningSeat.setScreening(screening);

        final var anotherScreening = new Screening();
        anotherScreening.setId(2L);
        final var anotherScreeningSeat = new ScreeningSeat();
        anotherScreeningSeat.setScreening(anotherScreening);

        final var reservation = new Reservation();
        reservation.setReservedSeats(List.of(screeningSeat, anotherScreeningSeat));
        reservation.setScreening(screening);

        //when
        final var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.createReservation(reservation)
        );


        //then
        Assertions.assertEquals("Reservation should be made for one screening", thrown.getMessage());
    }

    @Test
    void givenReservationTimeLessThan15MinutesBeforeScreeningWhenCreateReservationThenExceptionIsThrown() {
        //given
        final var screeningTime = TestConfig.fixedClock().instant().atOffset(UTC);
        final var screening = new Screening();
        screening.setScreeningTime(screeningTime);
        final var screeningSeat = new ScreeningSeat();
        screeningSeat.setScreening(screening);

        final var reservation = new Reservation();
        reservation.setReservedSeats(List.of(screeningSeat));
        reservation.setScreening(screening);
        reservation.setReservationTime(screeningTime.minus(Duration.ofMinutes(5)));

        //when
        final var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.createReservation(reservation)
        );

        //then
        Assertions.assertEquals("Reservation should be made at least 15 minutes before screening time", thrown.getMessage());
    }

    @Test
    void givenReservationThatLeavesEmptySpaceInRowWhenCreateReservationThenExceptionIsThrown() {
        //given
        final var screeningTime = TestConfig.fixedClock().instant().atOffset(UTC);
        final var screening = new Screening();
        screening.setScreeningTime(screeningTime);

        final var seat1 = new Seat();
        seat1.setRow(1);
        seat1.setNumber(1);
        final var seat2 = new Seat();
        seat2.setRow(1);
        seat2.setNumber(2);
        final var seat3 = new Seat();
        seat3.setRow(1);
        seat3.setNumber(3);

        final var screeningSeat1 = new ScreeningSeat();
        screeningSeat1.setSeat(seat1);
        screeningSeat1.setReservation(new Reservation());
        screeningSeat1.setScreening(screening);
        final var screeningSeat2 = new ScreeningSeat();
        screeningSeat2.setSeat(seat2);
        screeningSeat2.setScreening(screening);
        final var screeningSeat3 = new ScreeningSeat();
        screeningSeat3.setSeat(seat3);
        screeningSeat3.setScreening(screening);

        when(reservationRepository.save(any())).thenAnswer(invocation -> {
            final var reservation = (Reservation) invocation.getArgument(0);
            reservation.getReservedSeats().forEach(seat -> seat.setReservation(reservation));
            return reservation;
        });
        when(screeningSeatRepository.findByScreening_Id(any())).thenReturn(List.of(screeningSeat1, screeningSeat2, screeningSeat3));

        final var reservation = new Reservation();
        reservation.setReservedSeats(List.of(screeningSeat3));
        reservation.setScreening(screening);
        reservation.setReservationTime(screeningTime.minus(Duration.ofHours(1)));

        //when
        final var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.createReservation(reservation)
        );

        //then
        Assertions.assertEquals("Reservation should not leave empty places in rows", thrown.getMessage());
    }

}