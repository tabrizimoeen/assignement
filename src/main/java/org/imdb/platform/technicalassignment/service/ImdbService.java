package org.imdb.platform.technicalassignment.service;

import org.imdb.platform.technicalassignment.component.ScoreCalculator;
import org.imdb.platform.technicalassignment.index.IndexStorage;
import org.imdb.platform.technicalassignment.model.Rating;
import org.imdb.platform.technicalassignment.model.Title;
import org.imdb.platform.technicalassignment.model.dto.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImdbService {

    private final IndexStorage store;

    public ImdbService(IndexStorage store) {
        this.store = store;
    }

    public List<ServiceResponse>
    sameDirectorWriterAlive() {

        List<ServiceResponse> result =
                new ArrayList<>();

        for (String titleId :
                store.titlesById.keySet()) {

            Set<String> directors =
                    store.titleToDirectors.get(titleId);

            Set<String> writers =
                    store.titleToWriters.get(titleId);

            if (directors == null
                    || writers == null) {

                continue;
            }

            for (String director : directors) {
                if (!writers.contains(director)) {
                    continue;
                }
                if (!store.alivePeople.contains(director)) {
                    continue;
                }

                Title title =
                        store.titlesById.get(titleId);

                if (title == null) {
                    continue;
                }
                result.add(
                        new ServiceResponse(
                                title.getId(),
                                title.getTitle(),
                                title.getYear()
                        )
                );
            }
        }
        return result;
    }


    public List<ServiceResponse>
    commonTitles(
            String actor1,
            String actor2
    ) {

        Set<String> a =
                store.actorToTitles.getOrDefault(
                        actor1,
                        Collections.emptySet()
                );

        Set<String> b =
                store.actorToTitles.getOrDefault(
                        actor2,
                        Collections.emptySet()
                );

        Set<String> smaller =
                a.size() < b.size() ? a : b;

        Set<String> larger =
                a.size() < b.size() ? b : a;

        List<ServiceResponse> result =
                new ArrayList<>();

        for (String titleId : smaller) {

            if (!larger.contains(titleId)) {
                continue;
            }

            Title title =
                    store.titlesById.get(titleId);

            if (title == null) {
                continue;
            }

            result.add(
                    new ServiceResponse(
                            title.getId(),
                            title.getTitle(),
                            title.getYear()
                    )
            );
        }

        return result;
    }

    public Map<Short, ServiceResponse>
    bestTitlesPerYear(String genre) {

        Map<Short, List<String>> byYear =
                store.genreYearTitles.getOrDefault(
                        genre,
                        Collections.emptyMap()
                );

        Map<Short, ServiceResponse> result =
                new TreeMap<>();

        for (Map.Entry<Short, List<String>> entry
                : byYear.entrySet()) {

            short year = entry.getKey();

            double bestScore = -1;

            Title bestTitle = null;

            for (String titleId : entry.getValue()) {

                Rating rating =
                        store.ratingsByTitle.get(titleId);

                Title title =
                        store.titlesById.get(titleId);

                if (rating == null
                        || title == null) {

                    continue;
                }

                double score =
                        ScoreCalculator.calculate(
                                rating.getRating(),
                                rating.getVotes()
                        );

                if (score > bestScore) {

                    bestScore = score;

                    bestTitle = title;
                }
            }

            if (bestTitle != null) {

                result.put(
                        year,
                        new ServiceResponse(
                                bestTitle.getId(),
                                bestTitle.getTitle(),
                                bestTitle.getYear()
                        )
                );
            }
        }

        return result;
    }
}