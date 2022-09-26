package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Catalog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class CatalogController {


    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/{genre}")
        ResponseEntity<Catalog> getMoviesAndSeriesByGenre(@PathVariable String genre) {
        return catalogService.getMoviesAndSeriesByGenre(genre);
    }

    // Endpoint para obtener datos desde catalogDB
    @GetMapping("/fromcatalog/{genre}")
        ResponseEntity<Catalog> getCatalogByGenre (@PathVariable String genre) {
        return catalogService.getCatalogByGenre(genre);
    }

    // Endpoint para probar patrón CircuitBreaker
    @GetMapping("/witherror/{genre}")
        ResponseEntity<Catalog> getMoviesAndSeriesByGenreError (@PathVariable String genre,
                                                                @RequestParam Boolean movieErrors,
                                                                @RequestParam Boolean seriesErrors) {
        return catalogService.getMoviesAndSeriesByGenreError(genre, movieErrors, seriesErrors);
    }
}