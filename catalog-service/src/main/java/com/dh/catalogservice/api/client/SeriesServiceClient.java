package com.dh.catalogservice.api.client;

import com.dh.catalogservice.domain.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SeriesServiceClient {

    @GetMapping("/series/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable(value = "genre") String genre);

    // Request para probar el patrón CircuitBreaker
    @GetMapping("series/witherror/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenreError(@PathVariable(value = "genre") String genre,
                                                      @RequestParam("seriesErrors") Boolean seriesErrors);

}


