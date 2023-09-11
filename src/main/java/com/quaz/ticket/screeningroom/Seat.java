package com.quaz.ticket.screeningroom;

import com.quaz.ticket.persistence.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat extends AbstractEntity<Long> {

    @ManyToOne(optional = false)
    private ScreeningRoom screeningRoom;

    @Column(name = "row", nullable = false)
    private Integer row;

    @Column(name = "number", nullable = false)
    private Integer number;

}