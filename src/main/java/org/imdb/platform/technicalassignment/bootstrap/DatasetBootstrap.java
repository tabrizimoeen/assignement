package org.imdb.platform.technicalassignment.bootstrap;


import jakarta.annotation.PostConstruct;
import org.imdb.platform.technicalassignment.service.loader.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    public void init() throws IOException {

        long start = System.currentTimeMillis();
        //TODO should put the file path in th config and read files from specified directory. it should not be hardcoded like this
        ClassPathResource resource = new ClassPathResource("dataset/title.basics.tsv.gz");

        basicsLoader.load(
                resource.getInputStream()
        );
        resource = new ClassPathResource("dataset/title.ratings.tsv.gz");
        ratingsLoader.load(
                resource.getInputStream()

        );
        resource = new ClassPathResource("dataset/title.crew.tsv.gz");

        crewLoader.load(
                resource.getInputStream()
        );
        resource = new ClassPathResource("dataset/title.principals.tsv.gz");

        principalsLoader.load(
                resource.getInputStream()
        );
        resource = new ClassPathResource("dataset/name.basics.tsv.gz");

        personLoader.load(
                resource.getInputStream()
        );

        System.out.println(
                "Loaded in ms: "
                        + (System.currentTimeMillis() - start)
        );
    }
}