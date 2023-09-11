package com.quaz.ticket.movie;

import com.quaz.ticket.persistence.AbstractEntity;
import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import java.time.Duration;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "duration", nullable = false, columnDefinition = "interval")
    private Duration duration;

}