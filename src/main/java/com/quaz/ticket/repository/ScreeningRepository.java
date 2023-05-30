package com.quaz.ticket.repository;

import com.quaz.ticket.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("select s from Screening s where s.screeningTime between ?1 and ?2 order by s.movie.title, s.screeningTime")
    List<Screening> findByScreeningTimeBetween(OffsetDateTime from, OffsetDateTime to);

}