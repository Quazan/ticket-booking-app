package com.quaz.ticket.service;

import com.quaz.ticket.entity.Screening;
import com.quaz.ticket.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public List<Screening> listByScreeningTime(OffsetDateTime from, OffsetDateTime to) {
        return screeningRepository.findByScreeningTimeBetweenOrderByMovieTitleAscScreeningTimeAsc(from, to);
    }

    public Optional<Screening> fetch(Long id) {
        return screeningRepository.findById(id);
    }
}
