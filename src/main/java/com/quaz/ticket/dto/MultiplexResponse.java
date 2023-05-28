package com.quaz.ticket.dto;

import java.io.Serializable;

public record MultiplexResponse(
        Long id,
        String name
) implements Serializable {

}