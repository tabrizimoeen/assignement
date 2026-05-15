package org.imdb.platform.technicalassignment.service.loader;


import org.imdb.platform.technicalassignment.component.FastTsvParser;
import org.imdb.platform.technicalassignment.index.IndexStore;
import org.imdb.platform.technicalassignment.model.enums.LifeStatus;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class PersonLoader extends BaseLoader {
    private final IndexStore store;

    public PersonLoader(IndexStore store) {
        this.store = store;
    }

    public void load(InputStream inputStream) {

        read(inputStream, line -> {

            String id =
                    FastTsvParser.column(line, 0);

            String birthYear =
                    FastTsvParser.column(line, 2);

            String deathYear =
                    FastTsvParser.column(line, 3);


           LifeStatus lifeStatus= resolveLifeStatus(birthYear,deathYear);
           if(lifeStatus == LifeStatus.ALIVE){
               store.alivePeople.add(id);
           }
        });
    }
    private LifeStatus resolveLifeStatus(String birthYear, String deathYear) {

        if(!"\\N".equals(deathYear)){
            return LifeStatus.DEAD;
        }
        if(!"\\N".equals(birthYear)){
            return LifeStatus.ALIVE;
        }
        return LifeStatus.UNKNOWN;
    }
}