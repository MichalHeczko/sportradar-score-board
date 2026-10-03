package com.michalheczko.sportradar.scoreboard;

public record Match(String homeTeam, String awayTeam, int homeScore, int awayScore) {

    public Match {
        if (homeTeam == null || homeTeam.isBlank()) {
            throw new IllegalArgumentException("Home team name must not be blank");
        }
        if (awayTeam == null || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Away team name must not be blank");
        }
    }

    public boolean isBetween(String homeTeam, String awayTeam) {
        return this.homeTeam.equals(homeTeam) && this.awayTeam.equals(awayTeam);
    }

    public int totalScore() {
        return homeScore + awayScore;
    }
}
