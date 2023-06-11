package com.quaz.ticket.screening;

import java.io.Serializable;

public record ScreeningRoomResponse(
        Long id,
        String name
) implements Serializable {

}