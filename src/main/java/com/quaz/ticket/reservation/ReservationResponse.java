package com.quaz.ticket.reservation;

import com.quaz.ticket.screeningseat.ScreeningSeatEmbeddedResponse;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record ReservationResponse(
        Long id,
        OffsetDateTime reservationTime,
        String customerName,
        String customerSurname,
        BigDecimal totalPrice,
        List<ScreeningSeatEmbeddedResponse> reservedSeats //Add new response
) implements Serializable {

}