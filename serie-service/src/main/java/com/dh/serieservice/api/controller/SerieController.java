package com.dh.serieservice.api.controller;

import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.model.Serie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @Value("${server.port}")
    private String puerto;

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre, HttpServletResponse response) {
        response.addHeader("port", puerto);
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping("/save")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(serieService.save(serie));
    }

    @GetMapping("/witherror/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenreError(@PathVariable(value = "genre") String genre,
                                                      @RequestParam("seriesErrors") Boolean seriesErrors) {
        return ResponseEntity.ok().body(serieService.findByGenreError(genre, seriesErrors));
    }
}
