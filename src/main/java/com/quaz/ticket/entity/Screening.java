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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "screenings")
public class Screening extends AbstractPersistable<Long> {

    @ManyToOne(optional = false)
    private Movie movie;

    @ManyToOne(optional = false)
    private Multiplex multiplex;

    @Column(name = "screening_time", nullable = false)
    private OffsetDateTime screeningTime;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private List<ScreeningSeat> screeningSeats = new ArrayList<>();
}