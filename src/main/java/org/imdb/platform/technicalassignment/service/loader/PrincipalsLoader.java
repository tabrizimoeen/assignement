package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.TsvFileParser;
import org.imdb.platform.technicalassignment.index.IndexStorage;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashSet;

@Component
public class PrincipalsLoader extends BaseLoader {
    private final IndexStorage store;

    public PrincipalsLoader(IndexStorage store) {
        this.store = store;
    }

    public void load(InputStream inputStream) {

        read(inputStream, line -> {

            String category =
                    TsvFileParser.column(line, 3);

            if (!"actor".equals(category)
                    && !"actress".equals(category)) {

                return;
            }

            String titleId =
                    TsvFileParser.column(line, 0);

            if (!store.validTitles.contains(titleId)) {
                return;
            }

            String actorId =
                    TsvFileParser.column(line, 2);

            store.actorToTitles
                    .computeIfAbsent(
                            actorId,
                            x -> new HashSet<>()
                    )
                    .add(titleId);
        });
    }
}