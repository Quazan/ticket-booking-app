package com.quaz.ticket.mapper;

import com.quaz.ticket.dto.MovieResponse;
import com.quaz.ticket.entity.Movie;
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