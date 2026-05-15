package org.imdb.platform.technicalassignment.index;

import org.imdb.platform.technicalassignment.model.Rating;
import org.imdb.platform.technicalassignment.model.Title;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IndexStorage {


    public final Set<String> validTitles =
            ConcurrentHashMap.newKeySet();

    public final Map<String, Title> titlesById =
            new ConcurrentHashMap<>();

    public final Map<String, Rating> ratingsByTitle =
            new ConcurrentHashMap<>();

    public final Set<String> alivePeople =
            ConcurrentHashMap.newKeySet();

    public final Map<String, Set<String>>
            titleToDirectors =
            new ConcurrentHashMap<>();

    public final Map<String, Set<String>>
            titleToWriters =
            new ConcurrentHashMap<>();

    public final Map<String, Set<String>>
            actorToTitles =
            new ConcurrentHashMap<>();

    public final Map<String,
            Map<Short, List<String>>>
            genreYearTitles =
            new ConcurrentHashMap<>();
}