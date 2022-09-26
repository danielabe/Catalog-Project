package com.dh.catalogservice.data;

import com.dh.catalogservice.api.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final CatalogService catalogService;

    private final MongoTemplate mongoTemplate;

    public DataLoader(CatalogService catalogService, MongoTemplate mongoTemplate) {
        this.catalogService = catalogService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        mongoTemplate.dropCollection("movies");
        mongoTemplate.dropCollection("series");

    }
}
