package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.client.MoviesServiceClient;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.repository.MovieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MoviesServiceClient moviesServiceClient;

    public MovieService(MovieRepository movieRepository, MoviesServiceClient moviesServiceClient) {
        this.movieRepository = movieRepository;
        this.moviesServiceClient = moviesServiceClient;
    }

    public MongoRepository<Movie, String> getRepository() {
        return movieRepository;
    };

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public ResponseEntity<List<Movie>> getMoviesByGenre(String genre) {
        log.info("Estamos por obtener las movies...");
        ResponseEntity<List<Movie>> movies = moviesServiceClient.getMoviesByGenre(genre);
        return movies;
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "movieFallbackMethod")
    @Retry(name = "movies")
    public List<Movie> getMoviesByGenreError(String genre, Boolean movieErrors) {
        log.info("Estamos por obtener las movies...");
        ResponseEntity<List<Movie>> movies = moviesServiceClient.getMoviesByGenreError(genre, movieErrors);
        return movies.getBody();
    }

    private List<Movie> movieFallbackMethod(CallNotPermittedException exception) {
        log.info("Circuit Breaker fue activado");
        return new ArrayList<>();
    }
}