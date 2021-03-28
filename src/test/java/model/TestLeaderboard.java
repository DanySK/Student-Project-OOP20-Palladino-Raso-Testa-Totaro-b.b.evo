package model;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.leaderboard.LeaderboardImpl;


class TestLeaderboard {

    private LeaderboardImpl leaderboard;
    private final Map<String, Integer> testMap = new HashMap<>();
    private static final int ALEX00_SCORE = 1800;
    private static final int ALEX00_SCORE_2 = 1300;
    private static final int JACK_SCORE = 1700;
    private static final int TOMMY_SCORE = 1500;
    private static final int MARCUS_SCORE = 300;

    @BeforeEach
    void initLeaderboard() {
        this.leaderboard = new LeaderboardImpl();
        this.leaderboard.addPlayer("Alex00", ALEX00_SCORE);
        this.leaderboard.addPlayer("-<Jack>-", JACK_SCORE);
        this.leaderboard.addPlayer("_Tommy_", TOMMY_SCORE);
        this.leaderboard.addPlayer("Marcus", MARCUS_SCORE);

        this.testMap.put("-<Jack>-", JACK_SCORE);
        this.testMap.put("Alex00", ALEX00_SCORE);
        this.testMap.put("_Tommy_", TOMMY_SCORE);
        this.testMap.put("Marcus", MARCUS_SCORE);
    }

    @Test
    void testAddPlayer() {
        assertEquals(this.leaderboard.getLeaderBoard(), testMap);
    }

    @Test
    void testOverWritePlayer() {

        this.leaderboard.addPlayer("Alex00", ALEX00_SCORE_2);
        this.testMap.put("Alex00", ALEX00_SCORE_2);

        assertEquals(this.leaderboard.getLeaderBoard(), testMap);
    }

    @Test
    void testRemovePlayer() {
        this.leaderboard.removePlayer("Alex00", ALEX00_SCORE);
        this.testMap.remove("Alex00", ALEX00_SCORE);

        assertEquals(this.leaderboard.getLeaderBoard(), testMap);
    }

    @Test
    void testRemoveNonExsistentPlayer() {
        //The player is not present in ranking
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.leaderboard.removePlayer("Peppe", ALEX00_SCORE);
        });
    }

    @Test
    void testGetTopPlayer() {
        assertEquals(Optional.of(Map.entry("Alex00", ALEX00_SCORE)).get(), this.leaderboard.getTopPlayer().get());
        this.leaderboard.removePlayer("Alex00", ALEX00_SCORE);
        assertEquals(Optional.of(Map.entry("-<Jack>-", JACK_SCORE)).get(), this.leaderboard.getTopPlayer().get());
    }

    @Test
    void testGetSecondPlayer() {
        assertEquals(Optional.of(Map.entry("-<Jack>-", JACK_SCORE)).get(), this.leaderboard.getSecondPlayer().get());
        this.leaderboard.removePlayer("Alex00", ALEX00_SCORE);
        assertEquals(Optional.of(Map.entry("_Tommy_", TOMMY_SCORE)).get(), this.leaderboard.getSecondPlayer().get());
    }

    @Test
    void testGetThirdPlayer() {
        assertEquals(Optional.of(Map.entry("_Tommy_", TOMMY_SCORE)).get(), this.leaderboard.getThirdPlayer().get());
        this.leaderboard.removePlayer("Alex00", ALEX00_SCORE);
        assertEquals(Optional.of(Map.entry("Marcus", MARCUS_SCORE)).get(), this.leaderboard.getThirdPlayer().get());
    }

    @Test
    void testEmptyPoudium() {
        this.leaderboard = new LeaderboardImpl();
        assertEquals(Optional.empty(), this.leaderboard.getTopPlayer());
        assertEquals(Optional.empty(), this.leaderboard.getSecondPlayer());
        assertEquals(Optional.empty(), this.leaderboard.getThirdPlayer());

        //Add first player
        this.leaderboard.addPlayer("Fausto", ALEX00_SCORE);
        assertEquals(Optional.of(Map.entry("Fausto", ALEX00_SCORE)), this.leaderboard.getTopPlayer());
        assertEquals(Optional.empty(), this.leaderboard.getSecondPlayer());
        assertEquals(Optional.empty(), this.leaderboard.getThirdPlayer());

        //Add second player
        this.leaderboard.addPlayer("Mario", TOMMY_SCORE);
        assertEquals(Optional.of(Map.entry("Fausto", ALEX00_SCORE)), this.leaderboard.getTopPlayer());
        assertEquals(Optional.of(Map.entry("Mario", TOMMY_SCORE)), this.leaderboard.getSecondPlayer());
        assertEquals(Optional.empty(), this.leaderboard.getThirdPlayer());

        //Add third Player
        this.leaderboard.addPlayer("Alex", TOMMY_SCORE);
        assertEquals(Optional.of(Map.entry("Fausto", ALEX00_SCORE)), this.leaderboard.getTopPlayer());
        assertEquals(Optional.of(Map.entry("Mario", TOMMY_SCORE)), this.leaderboard.getSecondPlayer());
        assertEquals(Optional.of(Map.entry("Alex", TOMMY_SCORE)), this.leaderboard.getThirdPlayer());
    }

    @Test
    void testOrder() {
        this.leaderboard = new LeaderboardImpl();
        this.leaderboard.addPlayer("Fausto", ALEX00_SCORE);
        this.leaderboard.addPlayer("Mario", TOMMY_SCORE);
        this.leaderboard.addPlayer("Alex", TOMMY_SCORE);
        this.leaderboard.sortByScore();

        assertEquals(Optional.of(Map.entry("Fausto", ALEX00_SCORE)), this.leaderboard.getTopPlayer());
        assertEquals(Optional.of(Map.entry("Alex", TOMMY_SCORE)), this.leaderboard.getSecondPlayer());
        assertEquals(Optional.of(Map.entry("Mario", TOMMY_SCORE)), this.leaderboard.getThirdPlayer());
    }


}
