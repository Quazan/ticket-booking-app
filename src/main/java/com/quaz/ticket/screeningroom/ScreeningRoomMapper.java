package com.quaz.ticket.screeningroom;

import com.quaz.ticket.screening.ScreeningRoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScreeningRoomMapper {

    ScreeningRoomResponse toResponse(ScreeningRoom screeningRoom);

}