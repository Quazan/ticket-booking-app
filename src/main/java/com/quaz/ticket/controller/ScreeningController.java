package com.quaz.ticket.controller;

import com.quaz.ticket.dto.ScreeningDetails;
import com.quaz.ticket.dto.ScreeningListRecord;
import com.quaz.ticket.dto.ScreeningRequestParameters;
import com.quaz.ticket.mapper.ScreeningMapper;
import com.quaz.ticket.service.ScreeningServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/screenings")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningServiceImpl screeningService;

    private final ScreeningMapper screeningMapper;

    @GetMapping
    public List<ScreeningListRecord> listScreenings(@RequestParam @Valid ScreeningRequestParameters requestParameters) {
        return screeningService.listByScreeningTime(requestParameters.from(), requestParameters.to()).stream()
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
