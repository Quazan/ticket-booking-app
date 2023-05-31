package com.quaz.ticket.controller;

import com.quaz.ticket.dto.ReservationRequest;
import com.quaz.ticket.dto.ReservationResponse;
import com.quaz.ticket.mapper.ReservationMapper;
import com.quaz.ticket.service.ReservationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    private final ReservationMapper reservationMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservationResponse createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        final var reservation = reservationService.createReservation(reservationMapper.toEntity(reservationRequest));
        return reservationMapper.toResponse(reservation);
    }

}
