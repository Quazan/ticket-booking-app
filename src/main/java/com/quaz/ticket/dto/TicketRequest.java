package com.quaz.ticket.dto;

import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public record TicketRequest(
        @PositiveOrZero
        Integer count,
        Long ticketTypeId
) implements Serializable {

}