package com.quaz.ticket.screening;

import com.quaz.ticket.movie.Movie;
import com.quaz.ticket.persistence.AbstractEntity;
import com.quaz.ticket.screeningroom.ScreeningRoom;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "screenings")
public class Screening extends AbstractEntity<Long> {

    @ManyToOne(optional = false)
    private Movie movie;

    @ManyToOne(optional = false)
    private ScreeningRoom screeningRoom;

    @Column(name = "screening_time", nullable = false)
    private OffsetDateTime screeningTime;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private List<ScreeningSeat> screeningSeats = new ArrayList<>();
}