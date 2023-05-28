package com.quaz.ticket.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public record ScreeningListRecord(
        Long id,
        String movieTitle,
        OffsetDateTime screeningTime
) implements Serializable {

}