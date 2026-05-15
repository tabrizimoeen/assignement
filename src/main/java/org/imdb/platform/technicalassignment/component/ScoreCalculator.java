package org.imdb.platform.technicalassignment.component;


public final class ScoreCalculator {

    private ScoreCalculator() {
    }

    public static double calculate(
            float rating,
            int votes
    ) {

        return rating * Math.log10(votes + 1);
    }
}