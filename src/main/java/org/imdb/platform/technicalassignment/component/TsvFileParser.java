package org.imdb.platform.technicalassignment.component;

public class TsvFileParser {
    private TsvFileParser() {
    }

    public static String column(
            String line,
            int target
    ) {

        int start = 0;
        int col = 0;

        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == '\t') {

                if (col == target) {
                    return line.substring(start, i);
                }

                col++;
                start = i + 1;
            }
        }

        return col == target
                ? line.substring(start)
                : "";
    }
}
