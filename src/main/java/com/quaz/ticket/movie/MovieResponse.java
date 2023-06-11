package com.quaz.ticket.movie;

import java.io.Serializable;

public record MovieResponse(
        Long id,
        String title,
        Long duration
) implements Serializable {

}