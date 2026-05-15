package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.TsvFileParser;
import org.imdb.platform.technicalassignment.index.IndexStorage;
import org.imdb.platform.technicalassignment.model.Rating;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class RatingsLoader extends BaseLoader {

    private final IndexStorage store;

    public RatingsLoader(IndexStorage store) {
        this.store = store;
    }

    public void load(InputStream  inputStream) {

        read(inputStream, line -> {

            String titleId =
                    TsvFileParser.column(line, 0);

            String ratingStr =
                    TsvFileParser.column(line, 1);

            String votesStr =
                    TsvFileParser.column(line, 2);

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