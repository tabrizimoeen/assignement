package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.FastTsvParser;
import org.imdb.platform.technicalassignment.index.IndexStore;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashSet;

@Component
public class PrincipalsLoader extends BaseLoader {
    private final IndexStore store;

    public PrincipalsLoader(IndexStore store) {
        this.store = store;
    }

    public void load(InputStream inputStream) {

        read(inputStream, line -> {

            String category =
                    FastTsvParser.column(line, 3);

            if (!"actor".equals(category)
                    && !"actress".equals(category)) {

                return;
            }

            String titleId =
                    FastTsvParser.column(line, 0);

            if (!store.validTitles.contains(titleId)) {
                return;
            }

            String actorId =
                    FastTsvParser.column(line, 2);

            store.actorToTitles
                    .computeIfAbsent(
                            actorId,
                            x -> new HashSet<>()
                    )
                    .add(titleId);
        });
    }
}