package com.quaz.ticket.reservation;

import com.quaz.ticket.validation.EqualNumberOfSeatsAndTickets;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@EqualNumberOfSeatsAndTickets
public record ReservationRequest(
        @NotNull
        Long screeningId,
        @NotBlank
        @Length(min = 3, max = 255)
        @Pattern(regexp = "^\\p{Lu}\\p{Ll}{2,}")
        String customerName,
        @NotBlank
        @Length(min = 3, max = 255)
        @Pattern(regexp = "^\\p{Lu}\\p{Ll}{2,}(-\\p{Lu}\\p{Ll}{2,})?$")
        String customerSurname,
        String voucherCode,
        @NotNull
        @Size(min = 1)
        Set<Long> reservedSeatIds,
        @NotNull
        @Size(min = 1)
        List<TicketRequest> tickets
) implements Serializable {

}