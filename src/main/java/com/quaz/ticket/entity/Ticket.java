package com.quaz.ticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.math.BigDecimal;
import java.time.Clock;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket extends AbstractPersistable<Long> {

    @ManyToOne(optional = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    private TicketType ticketType;

    public BigDecimal getPrice(Clock clock) {
        return ticketType.getCurrentPrice(clock);
    }

}