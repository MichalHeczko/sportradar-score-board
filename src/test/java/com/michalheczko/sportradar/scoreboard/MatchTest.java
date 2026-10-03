package com.michalheczko.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchTest {

    @Test
    void isBetweenMatchesSameTeamsInSameOrder() {
        assertTrue(new Match("Mexico", "Canada", 0, 0).isBetween("Mexico", "Canada"));
    }

    @Test
    void isBetweenDoesNotMatchSwappedTeams() {
        assertFalse(new Match("Mexico", "Canada", 0, 0).isBetween("Canada", "Mexico"));
    }

    @Test
    void isBetweenDoesNotMatchDifferentTeams() {
        assertFalse(new Match("Mexico", "Canada", 0, 0).isBetween("Spain", "Canada"));
    }
}
