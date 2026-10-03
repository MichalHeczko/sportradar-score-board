package com.michalheczko.sportradar.scoreboard;

public record Match(String homeTeam, String awayTeam, int homeScore, int awayScore) {

    public Match {
        requireTeamName(homeTeam, "Home");
        requireTeamName(awayTeam, "Away");
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("A team cannot play against itself: " + homeTeam);
        }
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative: " + homeScore + " - " + awayScore);
        }
    }

    private static void requireTeamName(String name, String side) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(side + " team name must not be blank");
        }
    }

    public boolean isBetween(String homeTeam, String awayTeam) {
        return this.homeTeam.equals(homeTeam) && this.awayTeam.equals(awayTeam);
    }

    public int totalScore() {
        return homeScore + awayScore;
    }
}
