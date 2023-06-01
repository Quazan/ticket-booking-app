package com.quaz.ticket.screening;

import com.quaz.ticket.screeningseat.ScreeningSeatEmbeddedResponse;
import com.quaz.ticket.movie.MovieResponse;
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