package org.imdb.platform.technicalassignment.service.loader;


import org.springframework.stereotype.Component;
import org.technical.assignment.component.FastTsvParser;
import org.technical.assignment.component.StringPool;
import org.technical.assignment.index.store.IndexStore;
import org.technical.assignment.model.Title;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class TitleBasicsLoader extends BaseLoader {

    private final IndexStore store;

    public TitleBasicsLoader(IndexStore store) {
        this.store = store;
    }

    public void load(Path path) {

        read(path, line -> {

            String type =
                    FastTsvParser.column(line, 1);

            if (!"movie".equals(type) &&  !"short".equals(type)) {
                return;
            }

            String id =
                    FastTsvParser.column(line, 0);

            store.validTitles.add(id);

            String title =
                    StringPool.pool(
                            FastTsvParser.column(line, 2)
                    );

            String yearStr =
                    FastTsvParser.column(line, 5);

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
                    FastTsvParser.column(line, 8);

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