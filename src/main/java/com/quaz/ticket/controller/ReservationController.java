package com.quaz.ticket.controller;

import com.quaz.ticket.dto.ReservationRequest;
import com.quaz.ticket.dto.ReservationResponse;
import com.quaz.ticket.mapper.ReservationMapper;
import com.quaz.ticket.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @GetMapping
    public List<ReservationResponse> listReservations() {
        return reservationService.listReservations().stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id)
                .map(reservationMapper::toResponse)
                .orElseThrow();
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        return reservationMapper.toResponse(reservationService.createReservation(reservationMapper.toEntity(reservationRequest)));
    }

}
