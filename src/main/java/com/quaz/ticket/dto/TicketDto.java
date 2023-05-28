package com.quaz.ticket.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.quaz.ticket.entity.Ticket}
 */
public record TicketDto(Long ticketTypeId) implements Serializable {

}