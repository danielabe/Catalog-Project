package com.dh.movieservice.api.service;

import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${queue.movie.name}")
    private String movieQueue;

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        log.info("La movie " + movie.getName() + " ha sido añadida a la base de datos movies.");
        rabbitTemplate.convertAndSend(movieQueue, movie);
        log.info("La movie " + movie.getName() + " ha sido enviada a la cola movieQueue.");
        return movieRepository.save(movie);
    }

    public List<Movie> findByGenreError(String genre, Boolean movieErrors) throws RuntimeException{
        if (movieErrors)
            throw new RuntimeException();
        return movieRepository.findByGenre(genre);
    }
}
