package com.quaz.ticket.dto;

import java.io.Serializable;

public record ScreeningSeatEmbeddedResponse(
        Long id,
        Integer row,
        Integer seatNumber,
        Boolean available
) implements Serializable {

}



