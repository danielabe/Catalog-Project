package com.dh.serieservice.api.service;

import com.dh.serieservice.domain.model.Serie;
import com.dh.serieservice.domain.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SerieService {

    private final SerieRepository serieRepository;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SerieService(SerieRepository serieRepository, RabbitTemplate rabbitTemplate) {
        this.serieRepository = serieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${queue.serie.name}")
    private String serieQueue;

    public List<Serie> findByGenre(String genre) {
        return serieRepository.findByGenre(genre);
    }

    public Serie save(Serie serie) {
        log.info("La serie " + serie.getName() + " ha sido añadida a la base de datos seriesDB.");
        rabbitTemplate.convertAndSend(serieQueue, serie);
        log.info("La serie " + serie.getName() + " ha sido enviada a la cola serieQueue.");
        return serieRepository.save(serie);
    }

    public List<Serie> findByGenreError(String genre, Boolean seriesErrors) throws RuntimeException{
        if (seriesErrors)
            throw new RuntimeException();
        return serieRepository.findByGenre(genre);
    }
}