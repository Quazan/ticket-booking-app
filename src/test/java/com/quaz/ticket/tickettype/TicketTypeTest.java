package com.quaz.ticket.tickettype;

import com.quaz.ticket.configuration.TestConfig;
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

class TicketTypeTest {

    @Test
    void givenNotWeekendDateWhenPriceIsCalculatedThenRegularPriceIsReturned() {
        //give
        TicketType ticketType = new TicketType();
        ticketType.setName("Test");
        ticketType.setRegularPrice(BigDecimal.valueOf(100));
        ticketType.setWeekendPrice(BigDecimal.valueOf(200));

        final var notWeekendDate = TestConfig.fixedClock().instant().atOffset(UTC).with(nextOrSame(THURSDAY))
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

        final var weekendDate = TestConfig.fixedClock().instant().atOffset(UTC).with(nextOrSame(SATURDAY))
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

        final var weekendDate = TestConfig.fixedClock().instant().atOffset(UTC).with(nextOrSame(FRIDAY))
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

        final var weekendDate = TestConfig.fixedClock().instant().atOffset(UTC).with(nextOrSame(SUNDAY))
                .with(LocalTime.of(23, 0))
                .withOffsetSameInstant(UTC);
        //when
        final var price = ticketType.getPrice(weekendDate);

        //then
        assertEquals(BigDecimal.valueOf(100), price);
    }

}