package com.quaz.ticket.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public record ReservationResponse(
        Long id,
        OffsetDateTime reservationTime,
        String customerName,
        String customerSurname
) implements Serializable {

}