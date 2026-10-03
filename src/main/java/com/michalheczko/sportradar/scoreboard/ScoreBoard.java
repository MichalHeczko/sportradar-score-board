package com.michalheczko.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();

    public List<Match> getSummary() {
        return matches;
    }

    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam, 0, 0));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (match.isBetween(homeTeam, awayTeam)) {
                matches.set(i, new Match(homeTeam, awayTeam, homeScore, awayScore));
                return;
            }
        }
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match -> match.isBetween(homeTeam, awayTeam));
    }
}
