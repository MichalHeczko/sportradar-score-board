package com.michalheczko.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreBoardTest {

    @Test
    void newScoreBoardHasEmptySummary() {
        ScoreBoard board = new ScoreBoard();
        assertTrue(board.getSummary().isEmpty());
    }

}
