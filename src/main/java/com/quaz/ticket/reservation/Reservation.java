package com.quaz.ticket.reservation;

import com.quaz.ticket.persistence.AbstractEntity;
import com.quaz.ticket.screening.Screening;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import com.quaz.ticket.voucher.Voucher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation extends AbstractEntity<Long> {

    @ManyToOne(optional = false)
    private Screening screening;

    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime reservationTime;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_surname", nullable = false)
    private String customerSurname;

    @ManyToOne
    private Voucher voucher;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "expiration_time", nullable = false)
    private OffsetDateTime expirationTime;

    @OneToMany(mappedBy = "reservation")
    private List<ScreeningSeat> reservedSeats = new ArrayList<>();

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    public Optional<Voucher> getVoucher() {
        return Optional.ofNullable(voucher);
    }

}