package com.quaz.ticket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket extends AbstractPersistable<Long> {

    @Column(name = "count", nullable = false)
    private Integer count;

    @ManyToOne(optional = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    private TicketType ticketType;

    public BigDecimal getPrice(OffsetDateTime dateTime) {
        return ticketType.getPrice(dateTime).multiply(BigDecimal.valueOf(count));
    }

}