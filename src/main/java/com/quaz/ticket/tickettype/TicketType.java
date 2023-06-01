package com.quaz.ticket.tickettype;

import com.quaz.ticket.persistence.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ticket_types")
public class TicketType extends AbstractEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "regular_price", nullable = false)
    private BigDecimal regularPrice;

    @Column(name = "weekend_price", nullable = false)
    private BigDecimal weekendPrice;

    public BigDecimal getPrice(OffsetDateTime currentDateTime) {
        final var dayOfWeek = currentDateTime.getDayOfWeek();

        if (Set.of(FRIDAY, SATURDAY, SUNDAY).contains(dayOfWeek)) {
            final var startDateTime = currentDateTime.with(previousOrSame(FRIDAY))
                    .with(LocalTime.of(14, 0))
                    .withOffsetSameInstant(UTC);

            final var endDateTime = currentDateTime.with(nextOrSame(SUNDAY))
                    .with(LocalTime.of(23, 0))
                    .withOffsetSameInstant(UTC);

            if (currentDateTime.isEqual(startDateTime) || (currentDateTime.isAfter(startDateTime) && currentDateTime.isBefore(endDateTime))) {
                return weekendPrice;
            }
        }
        return regularPrice;
    }

}
