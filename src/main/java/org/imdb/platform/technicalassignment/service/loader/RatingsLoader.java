package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.FastTsvParser;
import org.imdb.platform.technicalassignment.index.IndexStore;
import org.imdb.platform.technicalassignment.model.Rating;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class RatingsLoader extends BaseLoader {

    private final IndexStore store;

    public RatingsLoader(IndexStore store) {
        this.store = store;
    }

    public void load(Path path) {

        read(path, line -> {

            String titleId =
                    FastTsvParser.column(line, 0);

            String ratingStr =
                    FastTsvParser.column(line, 1);

            String votesStr =
                    FastTsvParser.column(line, 2);

            float rating;
            int votes;

            try {

                rating = Float.parseFloat(ratingStr);

                votes = Integer.parseInt(votesStr);

            } catch (Exception e) {
                return;
            }

            store.ratingsByTitle.put(
                    titleId,
                    new Rating(rating, votes)
            );
        });
    }
}