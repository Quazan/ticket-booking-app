package com.quaz.ticket.screening;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.time.OffsetDateTime;
import java.util.List;

class ScreeningServiceImplTest {

    private ScreeningRepository screeningRepository;
    private ScreeningServiceImpl screeningService;

    @BeforeEach
    void setUp() {
        screeningRepository = mock(ScreeningRepository.class);
        screeningService = new ScreeningServiceImpl(screeningRepository);
    }

    @Test
    void givenTimeRangeWhereToIsBeforeFromWhenExecutedExceptionIsThrown() {
        //given
        final var from = OffsetDateTime.MAX;
        final var to = OffsetDateTime.MIN;

        //when
        final var thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            screeningService.listByScreeningTime(from, to);
        });

        //then
        Assertions.assertEquals("From date must be before to date", thrown.getMessage());
    }

    @Test
    void givenCorrectTimeRangeWhenExecutedThenListIsReturned() {
        //given
        when(screeningRepository.findByScreeningTimeBetween(any(), any())).thenReturn(List.of());
        final var from = OffsetDateTime.MIN;
        final var to = OffsetDateTime.MAX;

        //when
        final var list = screeningService.listByScreeningTime(from, to);

        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(0));
        verify(screeningRepository, times(1)).findByScreeningTimeBetween(from, to);
    }

}