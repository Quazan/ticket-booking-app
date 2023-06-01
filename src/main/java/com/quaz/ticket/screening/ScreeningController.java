package com.quaz.ticket.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningServiceImpl screeningService;

    private final ScreeningMapper screeningMapper;

    @GetMapping
    public List<ScreeningListRecord> listScreenings(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) OffsetDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) OffsetDateTime to) {
        return screeningService.listByScreeningTime(from, to).stream()
                .map(screeningMapper::toListRecord)
                .toList();
    }

    @GetMapping("/{id}")
    public ScreeningDetails getScreening(@PathVariable Long id) {
        return screeningService.getById(id)
                .map(screeningMapper::toDetails)
                .orElseThrow();
    }

}
