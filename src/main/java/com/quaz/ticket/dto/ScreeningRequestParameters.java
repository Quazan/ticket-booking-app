package com.quaz.ticket.dto;

import com.quaz.ticket.validation.ToMoreRecentThenFrom;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;

@ToMoreRecentThenFrom
public record ScreeningRequestParameters(
        @FutureOrPresent
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        OffsetDateTime from,
        @Future
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        OffsetDateTime to) {

}

