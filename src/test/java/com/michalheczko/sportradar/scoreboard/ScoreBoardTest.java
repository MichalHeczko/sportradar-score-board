package com.michalheczko.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
