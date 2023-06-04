package com.quaz.ticket.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservations", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    private final ReservationMapper reservationMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ReservationResponse createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        final var reservation = reservationService.createReservation(reservationMapper.toEntity(reservationRequest));
        return reservationMapper.toResponse(reservation);
    }

}