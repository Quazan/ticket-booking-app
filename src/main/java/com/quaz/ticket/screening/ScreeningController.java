package com.quaz.ticket.screening;

import com.quaz.ticket.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/screenings", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningServiceImpl screeningService;

    private final ScreeningMapper screeningMapper;

    @GetMapping
    public List<ScreeningListRecord> listScreenings(
            @RequestParam @DateTimeFormat(iso = DATE) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DATE) OffsetDateTime to) {
        return screeningService.listByScreeningTime(from, to).stream()
                .map(screeningMapper::toListRecord)
                .toList();
    }

    @GetMapping("/{id}")
    public ScreeningDetails getScreening(@PathVariable Long id) {
        return screeningService.getById(id)
                .map(screeningMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(Screening.class, id));
    }

}
