package com.quaz.ticket.screening;

import com.quaz.ticket.movie.MovieMapper;
import com.quaz.ticket.screeningroom.ScreeningRoomMapper;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import com.quaz.ticket.screeningseat.ScreeningSeatRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {MovieMapper.class, ScreeningRoomMapper.class})
public abstract class ScreeningMapper {

    @Autowired
    private ScreeningSeatRepository screeningSeatRepository;

    @Mapping(target = "movieTitle", source = "movie.title")
    public  abstract ScreeningListRecord toListRecord(Screening screening);

    @Mapping(target = "seats", expression = "java(mapScreeningSeats(screening))")
    public abstract ScreeningDetails toDetails(Screening screening);

    protected List<ScreeningSeatEmbeddedResponse> mapScreeningSeats(Screening screening) {
        return screeningSeatRepository.findByScreening_IdOrderBySeat_RowAscSeat_NumberAsc(screening.getId()).stream()
                .map(this::toScreeningSeatEmbeddedResponse)
                .toList();
    }

    @Mapping(target = "row", source = "seat.row")
    @Mapping(target = "seatNumber", source = "seat.number")
    public abstract ScreeningSeatEmbeddedResponse toScreeningSeatEmbeddedResponse(ScreeningSeat screeningSeat);

}