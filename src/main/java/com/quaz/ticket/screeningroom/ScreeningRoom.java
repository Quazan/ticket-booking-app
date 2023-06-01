package com.quaz.ticket.screeningroom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "screening_rooms")
public class ScreeningRoom extends AbstractPersistable<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "screeningRoom")
    private List<Seat> seats = new ArrayList<>();

}