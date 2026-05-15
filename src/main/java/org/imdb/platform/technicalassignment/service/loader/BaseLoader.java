package org.imdb.platform.technicalassignment.service.loader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;

public abstract class BaseLoader {


    protected void read(
            InputStream inputStream,
            Consumer<String> consumer
    ) {

        try (
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        new GZIPInputStream(
                                                inputStream
                                        )
                                ),
                                1024 * 1024
                        )
        ) {

            String line;

            boolean first = true;

            while ((line = br.readLine()) != null) {

                if (first) {
                    first = false;
                    continue;
                }

                consumer.accept(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}