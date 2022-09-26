package com.dh.serieservice.domain.data;

import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.model.Chapter;
import com.dh.serieservice.domain.model.Season;
import com.dh.serieservice.domain.model.Serie;
import com.dh.serieservice.domain.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements ApplicationRunner {

    private final SerieService serieService;

    private final MongoTemplate mongoTemplate;

    public DataLoader(SerieRepository serieRepository, SerieService serieService, MongoTemplate mongoTemplate) {
        this.serieService = serieService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        mongoTemplate.dropCollection("series");

        var chapter1 = new Chapter(1L, "Pilot", 1, "http://www...");

        List<Chapter> chapters1 = new ArrayList<>();
        chapters1.add(chapter1);

        var season1 = new Season(1L, 1, chapters1);

        List<Season> seasons = new ArrayList<>();
        seasons.add(season1);

        var serie1 = new Serie();
        serie1.setName("Breaking Bad");
        serie1.setGenre("Accion");
        serie1.setSeasons(seasons);

        var chapter2 = new Chapter(2L, "Seven Thirty-Seven", 1, "http://www...");

        List<Chapter> chapters2 = new ArrayList<>();
        chapters2.add(chapter2);

        var season2 = new Season(2L, 2, chapters2);

        seasons.add(season2);

        var sDB1 = serieService.save(serie1);
        log.info(sDB1.toString());
    }
}
