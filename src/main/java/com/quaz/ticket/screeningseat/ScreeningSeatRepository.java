package com.quaz.ticket.screeningseat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreeningSeatRepository extends JpaRepository<ScreeningSeat, Long> {

    List<ScreeningSeat> findByScreening_IdOrderBySeat_RowAscSeat_NumberAsc(Long id);

    List<ScreeningSeat> findByScreening_Id(Long id);

}