package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.client.SeriesServiceClient;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.repository.SerieRepository;
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
public class SerieService {

    private final SerieRepository serieRepository;
    private final SeriesServiceClient seriesServiceClient;

    public SerieService(SerieRepository serieRepository, SeriesServiceClient seriesServiceClient) {
        this.serieRepository = serieRepository;
        this.seriesServiceClient = seriesServiceClient;
    }

    public MongoRepository<Serie, String> getRepository() {
        return serieRepository;
    };

    public Serie save(Serie serie) {
        return serieRepository.save(serie);
    }

    public ResponseEntity<List<Serie>> getSeriesByGenre(String genre) {
        log.info("Estamos por obtener las series...");
        ResponseEntity<List<Serie>> series = seriesServiceClient.getSeriesByGenre(genre);
        return series;
    }

    @CircuitBreaker(name = "series", fallbackMethod = "seriesFallbackMethod")
    @Retry(name = "series")
    public List<Serie> getSeriesByGenreError(String genre, Boolean seriesErrors) {
        log.info("Estamos por obtener las series...");
        ResponseEntity<List<Serie>> series = seriesServiceClient.getSeriesByGenreError(genre, seriesErrors);
        return series.getBody();
    }

    private List<Serie> seriesFallbackMethod(CallNotPermittedException exception) {
        log.info("Circuit Breaker fue activado");
        return new ArrayList<>();
    }

}
