package com.quaz.ticket.reservation;

import com.quaz.ticket.configuration.TestConfig;
import com.quaz.ticket.screening.Screening;
import com.quaz.ticket.screening.ScreeningRepository;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import com.quaz.ticket.screeningseat.ScreeningSeatRepository;
import com.quaz.ticket.testcontainers.EnableTestcontainers;
import com.quaz.ticket.tickettype.TicketType;
import com.quaz.ticket.tickettype.TicketTypeRepository;
import com.quaz.ticket.voucher.Voucher;
import com.quaz.ticket.voucher.VoucherRepository;
import com.quaz.ticket.voucher.VoucherType;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest(classes = TestConfig.class)
@EnableTestcontainers
@ExtendWith(MockitoExtension.class)
class ReservationMapperTest {

    @Autowired
    private Clock clock;

    @MockBean
    private ScreeningSeatRepository screeningSeatRepository;

    @MockBean
    private ScreeningRepository screeningRepository;

    @MockBean
    private VoucherRepository voucherRepository;

    @MockBean
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    void shouldReturnCorrectlyMappedEntity() {
        //given
        final var ticketType = new TicketType();
        ticketType.setId(1L);
        ticketType.setRegularPrice(BigDecimal.TEN);
        ticketType.setWeekendPrice(BigDecimal.valueOf(15));
        when(ticketTypeRepository.findById(any())).thenReturn(Optional.of(ticketType));

        final var screening = new Screening();
        screening.setId(1L);
        screening.setScreeningTime(clock.instant().atOffset(UTC).plus(Duration.ofHours(1)));
        when(screeningRepository.findById(any())).thenReturn(Optional.of(screening));

        final var screeningSeat1 = new ScreeningSeat();
        screeningSeat1.setId(1L);
        screeningSeat1.setScreening(screening);
        final var screeningSeat2 = new ScreeningSeat();
        screeningSeat2.setId(2L);
        screeningSeat2.setScreening(screening);
        when(screeningSeatRepository.findAllById(any())).thenReturn(List.of(screeningSeat1, screeningSeat2));

        final var voucher = new Voucher();
        voucher.setId(1L);
        voucher.setCode("5_AMOUNT");
        voucher.setType(VoucherType.AMOUNT);
        voucher.setAmount(BigDecimal.valueOf(5));
        when(voucherRepository.findFirstByCodeLike(any())).thenReturn(voucher);

        final var request = new ReservationRequest(1L, "Jan", "Bach", "5_AMOUNT",
                                                   Set.of(1L, 2L), List.of(new TicketRequest(2, 1L)));
        //when
        final var reservation = reservationMapper.toEntity(request);

        //then
        assertThat(reservation, is(notNullValue()));
        assertThat(reservation.getReservationTime(), is(clock.instant().atOffset(UTC)));
        assertThat(reservation.getExpirationTime(), is(screening.getScreeningTime()));
        assertThat(reservation.getCustomerName(), is("Jan"));
        assertThat(reservation.getCustomerSurname(), is("Bach"));
        assertThat(reservation.getTotalPrice(), is(BigDecimal.valueOf(25)));
        assertThat(reservation.getReservedSeats(), hasSize(2));
        assertThat(reservation.getVoucher().isPresent(), is(true));
        assertThat(reservation.getVoucher().get(), is(voucher));
        assertThat(reservation.getScreening(), is(screening));
        assertThat(reservation.getTickets(), hasSize(1));
        assertThat(reservation.getTickets().get(0).getCount(), is(2));
    }
}