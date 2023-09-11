package com.quaz.ticket.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public List<Screening> listByScreeningTime(OffsetDateTime from, OffsetDateTime to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("From date must be before to date");
        }
        return screeningRepository.findByScreeningTimeBetween(from, to);
    }

    @Override
    public Optional<Screening> getById(Long id) {
        return screeningRepository.findById(id);
    }

    @Override
    public List<Screening> listAll() {
        return screeningRepository.findAll();
    }

}
