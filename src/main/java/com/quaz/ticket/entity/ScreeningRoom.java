package com.quaz.ticket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@Entity
@Table(name = "screening_rooms")
public class ScreeningRoom extends AbstractPersistable<Long> {

    @Column(name = "name", nullable = false)
    private String name;

}