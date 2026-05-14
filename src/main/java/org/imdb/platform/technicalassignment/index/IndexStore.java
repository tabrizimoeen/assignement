package org.imdb.platform.technicalassignment.index;

import org.imdb.platform.technicalassignment.model.Rating;
import org.imdb.platform.technicalassignment.model.Title;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IndexStore {


    // valid movie ids
    public final Set<String> validTitles =
            ConcurrentHashMap.newKeySet();

    // titleId -> title
    public final Map<String, Title> titlesById =
            new ConcurrentHashMap<>();

    // titleId -> rating
    public final Map<String, Rating> ratingsByTitle =
            new ConcurrentHashMap<>();

    // alive people only
    public final Set<String> alivePeople =
            ConcurrentHashMap.newKeySet();

    // title -> directors
    public final Map<String, Set<String>>
            titleToDirectors =
            new ConcurrentHashMap<>();

    // title -> writers
    public final Map<String, Set<String>>
            titleToWriters =
            new ConcurrentHashMap<>();

    // actor -> titles
    public final Map<String, Set<String>>
            actorToTitles =
            new ConcurrentHashMap<>();

    // genre -> year -> titles
    public final Map<String,
            Map<Short, List<String>>>
            genreYearTitles =
            new ConcurrentHashMap<>();
}