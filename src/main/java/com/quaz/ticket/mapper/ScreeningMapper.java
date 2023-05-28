package com.quaz.ticket.mapper;

import com.quaz.ticket.dto.ScreeningDetails;
import com.quaz.ticket.dto.ScreeningListRecord;
import com.quaz.ticket.entity.Screening;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {MovieMapper.class, MultiplexMapper.class, ScreeningSeatMapper.class})
public interface ScreeningMapper {

    @Mapping(target = "movieTitle", source = "movie.title")
    ScreeningListRecord toListRecord(Screening screening);

    @Mapping(target = "seats", source = "screeningSeats")
    ScreeningDetails toDetails(Screening screening);

}