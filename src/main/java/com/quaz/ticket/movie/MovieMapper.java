package com.quaz.ticket.movie;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.time.Duration;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {

    MovieResponse toResponse(Movie movie);

    default Long toMinutes(Duration duration) {
        if (duration == null) {
            return null;
        }
        return duration.toMinutes();
    }

}