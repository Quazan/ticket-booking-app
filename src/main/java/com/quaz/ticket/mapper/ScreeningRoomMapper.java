package com.quaz.ticket.mapper;

import com.quaz.ticket.dto.ScreeningRoomResponse;
import com.quaz.ticket.entity.ScreeningRoom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScreeningRoomMapper {

    ScreeningRoomResponse toResponse(ScreeningRoom screeningRoom);

}