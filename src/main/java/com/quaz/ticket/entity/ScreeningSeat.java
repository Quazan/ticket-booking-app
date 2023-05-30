package com.quaz.ticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@Entity
@Table(name = "screening_seats")
public class ScreeningSeat extends AbstractPersistable<Long> {

    @ManyToOne(optional = false)
    private Screening screening;

    @ManyToOne(optional = false)
    private Seat seat;

    @ManyToOne
    private Reservation reservation;

    public boolean isAvailable() {
        return reservation == null;
    }

}