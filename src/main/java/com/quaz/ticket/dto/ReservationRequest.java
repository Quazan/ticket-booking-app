package com.quaz.ticket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.quaz.ticket.entity.Reservation}
 */
public record ReservationRequest(
        @NotBlank
        @Min(3)
        @Pattern(regexp = "^[A-Z][a-z]{2,}")
        String customerName,
        @NotBlank
        @Min(3)
        @Pattern(regexp = "^[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?$")
        String customerSurname,
        String voucherCode,
        @NotNull
        @Size(min = 1)
        List<Long> reservedSeats,
        @NotNull
        @Size(min = 1)
        List<TicketDto> tickets
) implements Serializable {

}