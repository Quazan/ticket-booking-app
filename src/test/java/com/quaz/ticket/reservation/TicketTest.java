package com.quaz.ticket.reservation;

import com.quaz.ticket.tickettype.TicketType;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

class TicketTest {

    @Test
    void givenNonZeroCountOfTicketsWhenTotalPriceIsCalculatedThenMultiplyOfTicketPriceIsReturned() {
        //given
        final var ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        Ticket ticket = new Ticket();
        ticket.setCount(4);
        ticket.setTicketType(ticketType);

        final var notWeekendDate  = OffsetDateTime.of(2023, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC).with(nextOrSame(THURSDAY))
                .with(LocalTime.of(13, 0))
                .withOffsetSameInstant(UTC);

        //when
        final var totalPrice = ticket.getPrice(notWeekendDate);

        //then
        assertEquals(BigDecimal.valueOf(400), totalPrice);
    }

    @Test
    void givenZeroCountOfTicketsWhenTotalPriceIsCalculatedThenMultiplyOfTicketPriceIsReturned() {
        //given
        final var ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        Ticket ticket = new Ticket();
        ticket.setCount(0);
        ticket.setTicketType(ticketType);

        final var notWeekendDate  = OffsetDateTime.of(2023, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC).with(nextOrSame(THURSDAY))
                .with(LocalTime.of(13, 0))
                .withOffsetSameInstant(UTC);

        //when
        final var totalPrice = ticket.getPrice(notWeekendDate);

        //then
        assertEquals(BigDecimal.ZERO, totalPrice);
    }

}