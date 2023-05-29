package com.quaz.ticket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ticket_types")
public class TicketType extends AbstractPersistable<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "regular_price", nullable = false)
    private BigDecimal regularPrice;

    @Column(name = "weekend_price", nullable = false)
    private BigDecimal weekendPrice;

    public BigDecimal getCurrentPrice(Clock clock) {
        OffsetDateTime currentDateTime = OffsetDateTime.now(clock);
        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

        if (Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(dayOfWeek)) {
            OffsetDateTime startDateTime = currentDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
                    .with(LocalTime.of(14, 0))
                    .withOffsetSameInstant(ZoneOffset.UTC);

            OffsetDateTime endDateTime = currentDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                    .with(LocalTime.of(23, 0))
                    .withOffsetSameInstant(ZoneOffset.UTC);

            if (currentDateTime.isAfter(startDateTime) && currentDateTime.isBefore(endDateTime)) {
                return weekendPrice;
            }
        }
        return regularPrice;
    }

}
