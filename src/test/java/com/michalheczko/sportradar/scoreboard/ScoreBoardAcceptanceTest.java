package com.michalheczko.sportradar.scoreboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreBoardAcceptanceTest {

    @Test
    @DisplayName("Summary matches the example from the assignment")
    void summaryMatchesAssignmentExample() {
        ScoreBoard board = new ScoreBoard();

        board.startMatch("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 0, 5);
        board.startMatch("Spain", "Brazil");
        board.updateScore("Spain", "Brazil", 10, 2);
        board.startMatch("Germany", "France");
        board.updateScore("Germany", "France", 2, 2);
        board.startMatch("Uruguay", "Italy");
        board.updateScore("Uruguay", "Italy", 6, 6);
        board.startMatch("Argentina", "Australia");
        board.updateScore("Argentina", "Australia", 3, 1);

        assertEquals(List.of(
                new Match("Uruguay", "Italy", 6, 6),
                new Match("Spain", "Brazil", 10, 2),
                new Match("Mexico", "Canada", 0, 5),
                new Match("Argentina", "Australia", 3, 1),
                new Match("Germany", "France", 2, 2)), board.getSummary());
    }
}