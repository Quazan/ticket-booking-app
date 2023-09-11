package com.quaz.ticket.screeningseat;

import com.quaz.ticket.persistence.AbstractEntity;
import com.quaz.ticket.reservation.Reservation;
import com.quaz.ticket.screening.Screening;
import com.quaz.ticket.screeningroom.Seat;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "screening_seats")
public class ScreeningSeat extends AbstractEntity<Long> {

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