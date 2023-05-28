package com.quaz.ticket.entity;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.time.Duration;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends AbstractPersistable<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "duration", nullable = false, columnDefinition = "interval")
    private Duration duration;

}