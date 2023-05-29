package com.quaz.ticket.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record ScreeningDetails(
        Long id,
        MovieResponse movie,
        ScreeningRoomResponse screeningRoom,
        List<ScreeningSeatEmbeddedResponse> seats,
        OffsetDateTime screeningTime
) implements Serializable {

}