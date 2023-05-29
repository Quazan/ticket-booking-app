package com.quaz.ticket.dto;

import com.quaz.ticket.validation.EqualNumberOfSeatsAndTickets;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;
import java.util.List;

@EqualNumberOfSeatsAndTickets
public record ReservationRequest(
        @NotBlank
        @Length(min = 3)
        @Pattern(regexp = "^\\p{Lu}\\p{Ll}{2,}")
        String customerName,
        @NotBlank
        @Length(min = 3)
        @Pattern(regexp = "^\\p{Lu}\\p{Ll}{2,}(-\\p{Lu}\\p{Ll}{2,})?$")
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