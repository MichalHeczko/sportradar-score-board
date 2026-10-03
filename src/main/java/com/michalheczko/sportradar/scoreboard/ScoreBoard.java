package com.michalheczko.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();

    public List<Match> getSummary() {
        return matches.reversed().stream().sorted(Comparator.comparingInt(Match::totalScore).reversed()).toList();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam, 0, 0);
        if (isPlaying(homeTeam)) {
            throw new IllegalStateException("Team is already playing: " + homeTeam);
        }

        if (isPlaying(awayTeam)) {
            throw new IllegalStateException("Team is already playing: " + awayTeam);
        }

        matches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (match.isBetween(homeTeam, awayTeam)) {
                matches.set(i, new Match(homeTeam, awayTeam, homeScore, awayScore));
                return;
            }
        }
        throw new IllegalStateException("No running match: " + homeTeam + " - " + awayTeam);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        if (!matches.removeIf(match -> match.isBetween(homeTeam, awayTeam))) {
            throw new IllegalStateException("No running match: " + homeTeam + " - " + awayTeam);
        }
    }

    private boolean isPlaying(String team) {
        return matches.stream().anyMatch(m -> m.homeTeam().equals(team) || m.awayTeam().equals(team));
    }
}
