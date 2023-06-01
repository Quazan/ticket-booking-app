package com.quaz.ticket.tickettype;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

class TicketTypeTest {

    @Test
    void givenNotWeekendDateWhenPriceIsCalculatedThenRegularPriceIsReturned() {
        //give
        TicketType ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        final var notWeekendDate  = OffsetDateTime.of(2023, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC).with(nextOrSame(THURSDAY))
                .with(LocalTime.of(13, 0))
                .withOffsetSameInstant(UTC);
        //when
        final var price = ticketType.getPrice(notWeekendDate);

        //then
        assertEquals(BigDecimal.valueOf(100), price);
    }

    @Test
    void givenWeekendDateWhenPriceIsCalculatedThenWeekendPriceIsReturned() {
        //give
        TicketType ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        final var weekendDate  = OffsetDateTime.of(2023, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC).with(nextOrSame(SATURDAY))
               .with(LocalTime.of(13, 0))
               .withOffsetSameInstant(UTC);
        //when
        final var price = ticketType.getPrice(weekendDate);

        //then
        assertEquals(BigDecimal.valueOf(200), price);
    }

    @Test
    void givenDateOfFriday2PMWhenPriceIsCalculatedThenWeekendPriceIsReturned() {
        //give
        TicketType ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        final var weekendDate  = OffsetDateTime.of(2023, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC).with(nextOrSame(FRIDAY))
                .with(LocalTime.of(14, 0))
                .withOffsetSameInstant(UTC);
        //when
        final var price = ticketType.getPrice(weekendDate);

        //then
        assertEquals(BigDecimal.valueOf(200), price);
    }

    @Test
    void givenDateOfSunday11PMWhenPriceIsCalculatedThenRegularPriceIsReturned() {
        //give
        TicketType ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        final var weekendDate  = OffsetDateTime.of(2023, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC).with(nextOrSame(SUNDAY))
                .with(LocalTime.of(23, 0))
                .withOffsetSameInstant(UTC);
        //when
        final var price = ticketType.getPrice(weekendDate);

        //then
        assertEquals(BigDecimal.valueOf(100), price);
    }

}