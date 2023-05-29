package com.quaz.ticket.dto;

import java.io.Serializable;

public record TicketRequest(Long ticketTypeId) implements Serializable {

}