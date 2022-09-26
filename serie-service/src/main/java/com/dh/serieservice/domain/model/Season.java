package com.dh.serieservice.domain.model;

import lombok.*;


import javax.persistence.Id;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Season {

    @Id
    private Long id;

    private int seasonNumber;

    private List<Chapter> chapters;
}
