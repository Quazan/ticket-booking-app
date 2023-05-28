package com.quaz.ticket.controller;

import com.quaz.ticket.dto.MovieResponse;
import com.quaz.ticket.mapper.MovieMapper;
import com.quaz.ticket.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieService;

    private final MovieMapper movieMapper;

    @GetMapping
    public List<MovieResponse> listMovies() {
        return movieService.findAll().stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    @GetMapping("{id}")
    public MovieResponse getMovie(@PathVariable Long id) {
        return movieService.findById(id)
                .map(movieMapper::toResponse)
                .orElseThrow();
    }

}
