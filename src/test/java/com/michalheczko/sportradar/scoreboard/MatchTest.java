package com.michalheczko.sportradar.scoreboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void rejectsInvalidHomeTeamName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Match(name, "Canada", 0, 0));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void rejectsInvalidAwayTeamName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Match("Mexico", name, 0, 0));
    }

    @Test
    void rejectsSameTeamOnBothSides() {
        assertThrows(IllegalArgumentException.class, () -> new Match("Mexico", "Mexico", 0, 0));
    }
}
