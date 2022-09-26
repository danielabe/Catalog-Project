package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "movies")
public class Movie {

    private String id;
    private String name;
    private String genre;
    private String urlStream;
}
