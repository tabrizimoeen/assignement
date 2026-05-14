package org.imdb.platform.technicalassignment.bootstrap;


import jakarta.annotation.PostConstruct;
import org.imdb.platform.technicalassignment.service.loader.*;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class DatasetBootstrap {

    private final TitleBasicsLoader basicsLoader;

    private final RatingsLoader ratingsLoader;

    private final CrewLoader crewLoader;

    private final PrincipalsLoader principalsLoader;

    private final PersonLoader personLoader;

    public DatasetBootstrap(
            TitleBasicsLoader basicsLoader,
            RatingsLoader ratingsLoader,
            CrewLoader crewLoader,
            PrincipalsLoader principalsLoader,
            PersonLoader personLoader
    ) {

        this.basicsLoader = basicsLoader;
        this.ratingsLoader = ratingsLoader;
        this.crewLoader = crewLoader;
        this.principalsLoader = principalsLoader;
        this.personLoader = personLoader;
    }

    @PostConstruct
    public void init() {

        long start = System.currentTimeMillis();

        basicsLoader.load(
                Paths.get("/home/moeen/dataset/title.basics.tsv.gz")
        );

        ratingsLoader.load(
                Paths.get("/home/moeen/dataset/title.ratings.tsv.gz")
        );

        crewLoader.load(
                Paths.get("/home/moeen/dataset/title.crew.tsv.gz")
        );

        principalsLoader.load(
                Paths.get("/home/moeen/dataset/title.principals.tsv.gz")
        );

        personLoader.load(
                Paths.get("/home/moeen/dataset/name.basics.tsv.gz")
        );

        System.out.println(
                "Loaded in ms: "
                        + (System.currentTimeMillis() - start)
        );
    }
}