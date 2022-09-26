package com.dh.catalogservice.queue;

import com.dh.catalogservice.api.service.MovieService;
import com.dh.catalogservice.domain.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MovieListener {

    private final MovieService movieService;

    public MovieListener(MovieService movieService) {
        this.movieService = movieService;
    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receiveMovie(@Payload Movie movie){
        var mov = movieService.save(movie);
        log.info("Se guardó en catalogDB la movie" + mov.toString());
    }
}
