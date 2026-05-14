package org.imdb.platform.technicalassignment.model.dto;

public record TitleResponse(
        String id,
        String title,
        int year
) {
}