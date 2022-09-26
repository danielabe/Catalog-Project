package com.dh.serieservice.domain.model;

import lombok.*;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Chapter {

    @Id
    private Long id;

    private String name;

    private int number;

    private String urlStream;

}
