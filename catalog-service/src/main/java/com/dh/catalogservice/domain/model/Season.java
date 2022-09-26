package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Season {

    private Long id;
    private int seasonNumber;
    private List<Chapter> chapters;
}