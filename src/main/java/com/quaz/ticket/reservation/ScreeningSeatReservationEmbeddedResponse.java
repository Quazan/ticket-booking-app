package com.quaz.ticket.reservation;

import java.io.Serializable;

public record ScreeningSeatReservationEmbeddedResponse (
        Integer row,
        Integer seatNumber
) implements Serializable {

}
