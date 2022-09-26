package com.dh.movieservice.domain.data;

import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final MovieService movieService;

    public DataLoader(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var m1 = movieService.save(new Movie(null, "Malignant", "Terror", "http://www..."));
        log.info(m1.toString());

        var m2 = movieService.save(new Movie(null, "The Butterfly Room", "Terror", "http://www..."));
        log.info(m2.toString());

        var m3 = movieService.save(new Movie(null, "Scream", "Terror", "http://www..."));
        log.info(m3.toString());

        var m4 = movieService.save(new Movie(null, "Annabelle", "Terror", "http://www..."));
        log.info(m4.toString());

        var m5 = movieService.save(new Movie(null, "Dakar", "Accion", "http://www..."));
        log.info(m5.toString());

        var m6 = movieService.save(new Movie(null, "Jurassic World", "Accion", "http://www..."));
        log.info(m6.toString());

        var m7 = movieService.save(new Movie(null, "Call Me by Your Name", "Romance", "http://www..."));
        log.info(m7.toString());

        var m8 = movieService.save(new Movie(null, "After We Collided", "Romance", "http://www..."));
        log.info(m8.toString());
    }
}
