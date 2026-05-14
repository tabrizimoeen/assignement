package org.imdb.platform.technicalassignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Title {
    private final String id;

    private final String title;

    private final short year;

}
