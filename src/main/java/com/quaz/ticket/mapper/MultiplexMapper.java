package com.quaz.ticket.mapper;

import com.quaz.ticket.dto.MultiplexResponse;
import com.quaz.ticket.entity.Multiplex;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MultiplexMapper {

    MultiplexResponse toResponse(Multiplex multiplex);

}