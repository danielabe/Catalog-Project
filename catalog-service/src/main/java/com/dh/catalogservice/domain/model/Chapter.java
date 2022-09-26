package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Chapter {

    private Long id;
    private String name;
    private int number;
    private String urlStream;
}