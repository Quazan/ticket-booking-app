package com.quaz.ticket.validation;

import com.quaz.ticket.reservation.ReservationRequest;
import com.quaz.ticket.reservation.TicketRequest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;

class EqualNumberOfSeatsAndTicketsValidatorTest {

    private final EqualNumberOfSeatsAndTicketsValidator validator = new EqualNumberOfSeatsAndTicketsValidator();

    @Test
    void givenDifferentCountOfTicketsAndSeatsWhenValidatedThenFalseIsReturned() {
        // given
        final var request = new ReservationRequest(2L, "Jan", "Bach", "none",
                                                   Set.of(1L), List.of(new TicketRequest(2, 1L)));

        // when
        boolean result = validator.isValid(request, null);

        // then
        assertFalse(result);
    }

    @Test
    void givenNullListOfTicketsWhenValidatedThenFalseIsReturned() {
        // given
        final var request = new ReservationRequest(2L, "Jan", "Bach", "none",
                                                   Set.of(1L), null);

        // when
        boolean result = validator.isValid(request, null);

        // then
        assertFalse(result);
    }

    @Test
    void givenNullListOfSeatsWhenValidatedThenFalseIsReturned() {
        // given
        final var request = new ReservationRequest(2L, "Jan", "Bach", "none",
                                                   null, List.of(new TicketRequest(2, 1L)));

        // when
        boolean result = validator.isValid(request, null);

        // then
        assertFalse(result);
    }

    @Test
    void givenEqualCountOfTicketsAndSeatsWhenValidatedThenTrueIsReturned() {
        // given
        final var request = new ReservationRequest(2L, "Jan", "Bach", "none",
                                                   Set.of(1L, 2L), List.of(new TicketRequest(1, 1L), new TicketRequest(1, 2L)));

        // when
        boolean result = validator.isValid(request, null);

        // then
        assertTrue(result);
    }
}