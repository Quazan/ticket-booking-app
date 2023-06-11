package com.quaz.ticket.screening;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ScreeningService {

    List<Screening> listByScreeningTime(OffsetDateTime from, OffsetDateTime to);

    Optional<Screening> getById(Long id);

    List<Screening> listAll();
}
