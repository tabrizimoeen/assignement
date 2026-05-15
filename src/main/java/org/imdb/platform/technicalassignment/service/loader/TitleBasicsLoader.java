package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.TsvFileParser;
import org.imdb.platform.technicalassignment.component.StringPool;
import org.imdb.platform.technicalassignment.index.IndexStorage;
import org.imdb.platform.technicalassignment.model.Title;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class TitleBasicsLoader extends BaseLoader {

    private final IndexStorage store;

    public TitleBasicsLoader(IndexStorage store) {
        this.store = store;
    }

    public void load(InputStream inputStream) {

        read(inputStream, line -> {

            String type =
                    TsvFileParser.column(line, 1);

            if (!"movie".equals(type) &&  !"short".equals(type)) {
                return;
            }

            String id =
                    TsvFileParser.column(line, 0);

            store.validTitles.add(id);

            String title =
                    StringPool.pool(
                            TsvFileParser.column(line, 2)
                    );

            String yearStr =
                    TsvFileParser.column(line, 5);

            short year;

            try {

                year =
                        "\\N".equals(yearStr)
                                ? -1
                                : Short.parseShort(yearStr);

            } catch (Exception e) {

                year = -1;
            }

            store.titlesById.put(
                    id,
                    new Title(id, title, year)
            );

            String genres =
                    TsvFileParser.column(line, 8);

            if (!"\\N".equals(genres)) {

                for (String genre :
                        genres.split(",")) {

                    genre =
                            StringPool.pool(genre);

                    store.genreYearTitles
                            .computeIfAbsent(
                                    genre,
                                    x -> new HashMap<>()
                            )
                            .computeIfAbsent(
                                    year,
                                    x -> new ArrayList<>()
                            )
                            .add(id);
                }
            }
        });
    }
}