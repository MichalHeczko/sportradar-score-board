package com.michalheczko.sportradar.scoreboard;

public record Match(String homeTeam, String awayTeam, int homeScore, int awayScore) {

    public boolean isBetween(String homeTeam, String awayTeam) {
        return this.homeTeam.equals(homeTeam) && this.awayTeam.equals(awayTeam);
    }

    public int totalScore() {
        return homeScore + awayScore;
    }
}
