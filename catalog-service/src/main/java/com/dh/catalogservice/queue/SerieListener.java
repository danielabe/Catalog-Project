package com.dh.catalogservice.queue;

import com.dh.catalogservice.api.service.SerieService;
import com.dh.catalogservice.domain.model.Serie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SerieListener {

    private final SerieService serieService;

    public SerieListener(SerieService serieService) {
        this.serieService = serieService;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveSerie(@Payload Serie serie){
        var ser = serieService.save(serie);
        log.info("Se guardó en catalogDB la serie" + ser.toString());
    }
}
