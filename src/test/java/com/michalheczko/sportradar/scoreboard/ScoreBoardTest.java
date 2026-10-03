package com.michalheczko.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    private final ScoreBoard board = new ScoreBoard();

    @Test
    void newScoreBoardHasEmptySummary() {
        assertTrue(board.getSummary().isEmpty());
    }

    @Test
    void startMatchAppearsInSummaryWithZeroScore() {
        board.startMatch("Mexico", "Canada");
        assertEquals(List.of(new Match("Mexico", "Canada", 0, 0)), board.getSummary());
    }

    @Test
    void updateMatchScore() {
        board.startMatch("Mexico", "Canada");

        board.updateScore("Mexico", "Canada", 0, 1);

        assertEquals(List.of(new Match("Mexico", "Canada", 0, 1)), board.getSummary());
    }

    @Test
    void updateScoreKeepsMatchOrder() {
        board.startMatch("Mexico", "Canada");
        board.startMatch("Spain", "Brazil");

        board.updateScore("Mexico", "Canada", 0, 5);

        assertEquals(List.of(
                new Match("Mexico", "Canada", 0, 5),
                new Match("Spain", "Brazil", 0, 0)), board.getSummary());
    }

    @Test
    void finishMatch() {
        board.startMatch("Mexico", "Canada");
        board.finishMatch("Mexico", "Canada");

        assertTrue(board.getSummary().isEmpty());
    }

    @Test
    void finishMatchRemovesOnlyTheGivenMatch() {
        board.startMatch("Mexico", "Canada");
        board.startMatch("Spain", "Brazil");
        board.startMatch("Germany", "France");

        board.finishMatch("Spain", "Brazil");

        assertEquals(
                Set.of(new Match("Mexico", "Canada", 0, 0), new Match("Germany", "France", 0, 0)),
                Set.copyOf(board.getSummary()));
    }

    @Test
    void summaryIsOrderedByTotalScoreDescending() {
        board.startMatch("Mexico", "Canada");
        board.startMatch("Spain", "Brazil");
        board.startMatch("Germany", "France");

        board.updateScore("Mexico", "Canada", 0, 5);
        board.updateScore("Spain", "Brazil", 10, 2);

        assertEquals(List.of(
                new Match("Spain", "Brazil", 10, 2),
                new Match("Mexico", "Canada", 0, 5),
                new Match("Germany", "France", 0, 0)), board.getSummary());

    }

    @Test
    void matchesWithSameTotalScoreAreOrderedByMostRecentlyStarted() {
        board.startMatch("Germany", "France");
        board.startMatch("Argentina", "Australia");
        board.updateScore("Germany", "France", 2, 2);
        board.updateScore("Argentina", "Australia", 3, 1);

        assertEquals(List.of(
                new Match("Argentina", "Australia", 3, 1),
                new Match("Germany", "France", 2, 2)), board.getSummary());
    }

    @Test
    void rejectedUpdateLeavesScoreUnchanged() {
        board.startMatch("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 1, 0);

        assertThrows(IllegalArgumentException.class, () -> board.updateScore("Mexico", "Canada", -1, 0));

        assertEquals(List.of(new Match("Mexico", "Canada", 1, 0)), board.getSummary());
    }

}
