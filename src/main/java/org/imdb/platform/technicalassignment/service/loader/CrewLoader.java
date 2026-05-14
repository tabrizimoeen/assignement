package org.imdb.platform.technicalassignment.service.loader;

import org.imdb.platform.technicalassignment.component.FastTsvParser;
import org.imdb.platform.technicalassignment.index.IndexStore;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class CrewLoader extends BaseLoader {

    private final IndexStore store;

    public CrewLoader(IndexStore store) {
        this.store = store;
    }

    public void load(Path path) {

        read(path, line -> {

            String titleId =
                    FastTsvParser.column(line, 0);

            if (!store.validTitles.contains(titleId)) {
                return;
            }

            store.titleToDirectors.put(
                    titleId,
                    parse(
                            FastTsvParser.column(line, 1)
                    )
            );

            store.titleToWriters.put(
                    titleId,
                    parse(
                            FastTsvParser.column(line, 2)
                    )
            );
        });
    }

    private Set<String> parse(String value) {

        if ("\\N".equals(value)) {
            return Set.of();
        }

        return new HashSet<>(
                Arrays.asList(value.split(","))
        );
    }
}