package com.quaz.ticket.service;

import com.quaz.ticket.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Clock;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final Clock clock;

    public BigDecimal calculateTotalPrice(Reservation reservation) {
        final var totalPrice = reservation.getTickets().stream()
                .map(ticket -> ticket.getPrice(clock))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return reservation.getVoucher().apply(totalPrice);
    }

}
