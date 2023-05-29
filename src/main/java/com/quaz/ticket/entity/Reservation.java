package com.quaz.ticket.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation extends AbstractPersistable<Long> {

    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime reservationTime;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_surname", nullable = false)
    private String customerSurname;

    @ManyToOne
    private Voucher voucher;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ScreeningSeat> reservedSeats = new ArrayList<>();

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

}