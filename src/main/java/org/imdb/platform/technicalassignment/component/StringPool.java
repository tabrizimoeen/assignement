package org.imdb.platform.technicalassignment.component;

import java.util.concurrent.ConcurrentHashMap;

public class StringPool {

    private static final ConcurrentHashMap<String, String>
            pool = new ConcurrentHashMap<>();

    private StringPool() {}

    public static String pool(String value) {

        return pool.computeIfAbsent(
                value,
                x -> x
        );
    }
}