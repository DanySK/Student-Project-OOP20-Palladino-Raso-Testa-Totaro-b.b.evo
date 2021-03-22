package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.leaderboard.PlayerImpl;

/**
 * Class that test the PlayerClass.
 */
public class TestPlayer {

    private static final String PLAYER_NAME = "Alex";
    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int PLAYER_SCORE = 50;
    private static final int PLAYER_LIFE = 3;
    private static final int ESPECTED_PLAYER_INCREASE_SCORE = 100;
    private static final int ESPECTED_PLAYER_DECREASE_SCORE = 0;
    private static final int ESPECTED_PLAYER_DECREASE_LIFE = 1;
    private static final int SCORE_OPERATION = 10;
    private static final String SECOND_NAME_PLAYER = "Jango";
    private PlayerImpl currentPlayer;

    /**
     * Method that initialize PlayerImpl.
     */
    @BeforeEach
    void initPlayer() {
        this.currentPlayer = new PlayerImpl(PLAYER_NAME, PLAYER_SCORE, PLAYER_LIFE);
    }

    @Test
    void testIncreaseScore() {
       this.currentPlayer.scoreOperation(SCORE_OPERATION);
       this.currentPlayer.scoreOperation(SCORE_OPERATION);
       this.currentPlayer.scoreOperation(SCORE_OPERATION);
       this.currentPlayer.scoreOperation(SCORE_OPERATION);
       this.currentPlayer.scoreOperation(SCORE_OPERATION);
       assertEquals(ESPECTED_PLAYER_INCREASE_SCORE, this.currentPlayer.getScore());
    }

    @Test
    void testDecreaseScore() {
       this.currentPlayer.scoreOperation(-SCORE_OPERATION);
       this.currentPlayer.scoreOperation(-SCORE_OPERATION);
       this.currentPlayer.scoreOperation(-SCORE_OPERATION);
       this.currentPlayer.scoreOperation(-SCORE_OPERATION);
       this.currentPlayer.scoreOperation(-SCORE_OPERATION);
       assertEquals(ESPECTED_PLAYER_DECREASE_SCORE, this.currentPlayer.getScore());

       //Try to decrease score but it's always 0
       for (int i = 0; i < ESPECTED_PLAYER_INCREASE_SCORE; i++) {
           this.currentPlayer.scoreOperation(-SCORE_OPERATION);
       }
       assertEquals(ESPECTED_PLAYER_DECREASE_SCORE, this.currentPlayer.getScore());
    }

    @Test
    void testGetter() {
        assertEquals(this.currentPlayer.getAlias(), PLAYER_NAME);
        assertEquals(this.currentPlayer.getLife(), PLAYER_LIFE);
        assertEquals(this.currentPlayer.getScore(), PLAYER_SCORE);
        assertNotEquals(this.currentPlayer.getAlias(), SECOND_NAME_PLAYER);
        assertNotEquals(this.currentPlayer.getLife(), PLAYER_SCORE);
        assertNotEquals(this.currentPlayer.getScore(), SCORE_OPERATION);
    }

    @Test
    void testSamePlayer() {
        final PlayerImpl secondPlayer = new PlayerImpl(PLAYER_NAME, PLAYER_SCORE, PLAYER_LIFE);
        final PlayerImpl thirdPlayer = new PlayerImpl(PLAYER_NAME, SCORE_OPERATION, SCORE_OPERATION);
        final PlayerImpl fourtyPlayer = new PlayerImpl(SECOND_NAME_PLAYER, PLAYER_SCORE, PLAYER_LIFE);

        assertTrue(this.currentPlayer.equals(secondPlayer));
        assertTrue(this.currentPlayer.equals(thirdPlayer));
        assertFalse(this.currentPlayer.equals(fourtyPlayer));
    }

    @Test
    void testDecreaseLife() {
        this.currentPlayer.decreaseLife();
        this.currentPlayer.decreaseLife();
        assertEquals(ESPECTED_PLAYER_DECREASE_LIFE, this.currentPlayer.getLife());
        this.currentPlayer.decreaseLife();
        assertEquals(ZERO, this.currentPlayer.getLife());

        //Not negative life
        for (int i = 0; i < ESPECTED_PLAYER_INCREASE_SCORE; i++) {
            this.currentPlayer.decreaseLife();
        }

        assertEquals(ZERO, this.currentPlayer.getLife());
    }

    @Test
    void testIncreaseLife() {
        this.currentPlayer.decreaseLife();
        this.currentPlayer.decreaseLife();
        this.currentPlayer.decreaseLife();
        assertEquals(ZERO, this.currentPlayer.getLife());
        this.currentPlayer.increaseLife();
        this.currentPlayer.increaseLife();
        assertEquals(TWO, this.currentPlayer.getLife());

        //Not increase at the max value
        for (int i = 0; i < ESPECTED_PLAYER_INCREASE_SCORE; i++) {
            this.currentPlayer.increaseLife();
        }
        assertEquals(PLAYER_LIFE, this.currentPlayer.getLife());
    }

    @Test
    void testAlive() {
        assertTrue(this.currentPlayer.isAlive());
        this.currentPlayer.decreaseLife();
        assertTrue(this.currentPlayer.isAlive());
        this.currentPlayer.decreaseLife();
        assertTrue(this.currentPlayer.isAlive());
        this.currentPlayer.decreaseLife();
        assertFalse(this.currentPlayer.isAlive());
        this.currentPlayer.decreaseLife();
        assertFalse(this.currentPlayer.isAlive());
    }

    @Test
    void testToString() {
        assertEquals(this.currentPlayer.toString(), "[alias = " + PLAYER_NAME + ", score = " + PLAYER_SCORE + " ,life = " + PLAYER_LIFE + "]");
        System.out.println(this.currentPlayer.toString());
        assertNotEquals(this.currentPlayer.toString(), "[alias= " + PLAYER_NAME + ", score= " + PLAYER_SCORE + " ,life= " + PLAYER_LIFE + "]");
    }
}
