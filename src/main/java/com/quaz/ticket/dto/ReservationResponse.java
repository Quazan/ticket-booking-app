package com.quaz.ticket.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ReservationResponse(
        Long id,
        OffsetDateTime reservationTime,
        String customerName,
        String customerSurname,
        BigDecimal totalPrice
) implements Serializable {

}