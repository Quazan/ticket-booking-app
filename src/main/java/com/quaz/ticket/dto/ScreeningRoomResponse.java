package com.quaz.ticket.dto;

import java.io.Serializable;

public record ScreeningRoomResponse(
        Long id,
        String name
) implements Serializable {

}