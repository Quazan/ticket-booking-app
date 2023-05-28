package com.quaz.ticket.dto;

import java.io.Serializable;

public record MovieResponse(
        Long id,
        String title,
        Long duration
) implements Serializable {

}