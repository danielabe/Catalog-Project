package com.dh.serieservice.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "series")
public class Serie {

    @Id
    private String id;

    private String name;

    private String genre;

    private List<Season> seasons;
}
