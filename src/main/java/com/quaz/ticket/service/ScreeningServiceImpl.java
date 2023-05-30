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
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public List<Screening> listByScreeningTime(OffsetDateTime from, OffsetDateTime to) {
        return screeningRepository.findByScreeningTimeBetween(from, to);
    }

    @Override
    public Optional<Screening> getById(Long id) {
        return screeningRepository.findById(id);
    }

}
