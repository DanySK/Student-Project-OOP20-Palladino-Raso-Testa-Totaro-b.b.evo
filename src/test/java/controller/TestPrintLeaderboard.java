package controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.utilities.IOLeaderboard;
import model.leaderboard.Leaderboard;
import model.leaderboard.LeaderboardImpl;

class TestPrintLeaderboard {

    private Leaderboard rank;
    private Map<String, Integer> map;
    private static final int NUMBER_PLAYER = 100;

    @BeforeEach
    void initRank() {
        this.rank = new LeaderboardImpl();
        this.map = new HashMap<>();
        for (int i = 0; i < NUMBER_PLAYER; i++) {
            this.rank.addPlayer(String.valueOf(i), i);
            this.map.put(String.valueOf(i), i);
        }
        this.rank.sortByScore();
    }

    @Test
    void testCorrectPrintJsonFormat() {
        assertDoesNotThrow(() -> {
            IOLeaderboard.printInJsonFormat(this.rank.getLeaderBoard());
        });
    }

    @Test
    void testCorrectReadJsonFormat() {
        assertDoesNotThrow(() -> {
            final Map<String, Integer> map2 = IOLeaderboard.readLeaderboard();
            assertEquals(this.map, map2);
        });
    }

}
