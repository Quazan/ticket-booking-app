package com.quaz.ticket.screening;

import java.io.Serializable;

public record ScreeningSeatEmbeddedResponse(
        Long id,
        Integer row,
        Integer seatNumber,
        Boolean available
) implements Serializable {

}



