package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.repository.MovieRepository;
import com.dh.catalogservice.repository.SerieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CatalogService {

    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private final MovieService movieService;
    private final SerieService serieService;

    public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository, MovieService movieService, SerieService serieService) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
        this.movieService = movieService;
        this.serieService = serieService;
    }

    public ResponseEntity<Catalog> getMoviesAndSeriesByGenre(@PathVariable String genre) {
        ResponseEntity<List<Movie>> moviesResponse = movieService.getMoviesByGenre(genre);
        List<Movie> movies = moviesResponse.getBody();

        ResponseEntity<List<Serie>> seriesResponse = serieService.getSeriesByGenre(genre);
        List<Serie> series = seriesResponse.getBody();

        Catalog catalog = new Catalog(genre, movies, series);

        System.out.println("Puerto utilizado en movies: " + moviesResponse.getHeaders().get("port"));
        System.out.println("Puerto utilizado en series: " + seriesResponse.getHeaders().get("port"));
        return  ResponseEntity.ok().body(catalog);
    }

    // Método para obtener datos desde catalogDB
    public ResponseEntity<Catalog> getCatalogByGenre(@PathVariable String genre) {
        List<Movie> movies = movieRepository.getMoviesByGenre(genre);
        List<Serie> series = serieRepository.getSeriesByGenre(genre);
        Catalog catalog = new Catalog(genre, movies, series);
        return  ResponseEntity.ok().body(catalog);
    }

    // Método para probar el patrón CircuitBreaker
    public ResponseEntity<Catalog> getMoviesAndSeriesByGenreError(@PathVariable String genre,
                                                                  Boolean movieErrors,
                                                                  Boolean seriesErrors) {
        List<Movie> movies = movieService.getMoviesByGenreError(genre, movieErrors);
        List<Serie> series = serieService.getSeriesByGenreError(genre, seriesErrors);
        Catalog catalog = new Catalog(genre, movies, series);
        return  ResponseEntity.ok().body(catalog);
    }
}
