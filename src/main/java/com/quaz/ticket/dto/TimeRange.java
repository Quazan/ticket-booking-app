package com.quaz.ticket.dto;

import java.time.OffsetDateTime;

public interface TimeRange {

    OffsetDateTime from();

    OffsetDateTime to();

}
