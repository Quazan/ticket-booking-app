package com.quaz.ticket.screeningseat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScreeningSeatMapper {

    @Mapping(target = "row", source = "seat.row")
    @Mapping(target = "seatNumber", source = "seat.number")
    ScreeningSeatEmbeddedResponse toEmbeddedResponse(ScreeningSeat screeningSeat);

}