package com.dh.catalogservice.api.client;

import com.dh.catalogservice.domain.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MoviesServiceClient {

    @GetMapping("/movies/{genre}")
    ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable(value = "genre") String genre);

    // Request para probar el patrón CircuitBreaker
    @GetMapping("movies/witherror/{genre}")
    ResponseEntity<List<Movie>> getMoviesByGenreError(@PathVariable(value = "genre") String genre,
                                                      @RequestParam("movieErrors") Boolean movieErrors);

}
