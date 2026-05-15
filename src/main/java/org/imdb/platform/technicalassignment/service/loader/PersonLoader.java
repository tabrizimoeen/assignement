package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.TsvFileParser;
import org.imdb.platform.technicalassignment.index.IndexStorage;
import org.imdb.platform.technicalassignment.model.enums.LifeStatus;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class PersonLoader extends BaseLoader {
    private final IndexStorage store;

    public PersonLoader(IndexStorage store) {
        this.store = store;
    }

    public void load(InputStream inputStream) {

        read(inputStream, line -> {

            String id =
                    TsvFileParser.column(line, 0);

            String birthYear =
                    TsvFileParser.column(line, 2);

            String deathYear =
                    TsvFileParser.column(line, 3);


            LifeStatus lifeStatus = resolveLifeStatus(birthYear, deathYear);
            if (lifeStatus == LifeStatus.ALIVE) {
                store.alivePeople.add(id);
            }
        });
    }

    private LifeStatus resolveLifeStatus(String birthYear, String deathYear) {

        if (!"\\N".equals(deathYear)) {
            return LifeStatus.DEAD;
        }
        if (!"\\N".equals(birthYear)) {
            return LifeStatus.ALIVE;
        }
        return LifeStatus.UNKNOWN;
    }
}