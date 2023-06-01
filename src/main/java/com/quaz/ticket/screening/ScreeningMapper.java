package com.quaz.ticket.screening;

import com.quaz.ticket.screeningroom.ScreeningRoomMapper;
import com.quaz.ticket.screeningseat.ScreeningSeatMapper;
import com.quaz.ticket.movie.MovieMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {MovieMapper.class, ScreeningRoomMapper.class, ScreeningSeatMapper.class})
public interface ScreeningMapper {

    @Mapping(target = "movieTitle", source = "movie.title")
    ScreeningListRecord toListRecord(Screening screening);

    @Mapping(target = "seats", source = "screeningSeats")
    ScreeningDetails toDetails(Screening screening);

}