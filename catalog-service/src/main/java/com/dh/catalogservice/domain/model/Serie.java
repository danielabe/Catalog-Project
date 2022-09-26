package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "series")
public class Serie {

    private String id;
    private String name;
    private String genre;
    private List<Season> seasons;
}
