package org.imdb.platform.technicalassignment.service.loader;

import org.imdb.platform.technicalassignment.index.IndexStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TitleServiceTest {

    private IndexStorage storage;

    @BeforeEach
    void setUp() {
        storage = new IndexStorage();
    }

    @Test
    void shouldReturnCommonTitlesBetweenActors() {

        storage.actorToTitles.put(
                "actor1",
                Set.of("test1", "test2")
        );

        storage.actorToTitles.put(
                "actor2",
                Set.of("test2", "test3")
        );

        Set<String> result =
                new HashSet<>(
                        storage.actorToTitles.get("actor1")
                );

        result.retainAll(
                storage.actorToTitles.get("actor2")
        );

        assertEquals(1, result.size());

        assertTrue(result.contains("test2"));
    }
}

