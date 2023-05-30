package com.quaz.ticket.repository;

import com.quaz.ticket.entity.ScreeningSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScreeningSeatRepository extends JpaRepository<ScreeningSeat, Long> {

    List<ScreeningSeat> findByScreening_Id(Long id);

}