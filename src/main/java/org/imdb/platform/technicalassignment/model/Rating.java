package org.imdb.platform.technicalassignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rating {
    private final float rating;

    private final int votes;
}