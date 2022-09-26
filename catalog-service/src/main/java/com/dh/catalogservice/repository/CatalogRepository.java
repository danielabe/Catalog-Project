package com.dh.catalogservice.repository;

import com.dh.catalogservice.domain.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {
}
