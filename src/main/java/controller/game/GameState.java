package controller.game;

import model.entities.GameBoard;
import model.leaderboard.Player;
import model.mapeditor.Level;
import model.utilities.Difficulty;

public interface GameState {

    /**
     * Set initial game state.
     */
    void init();

    /**
     * @return score values
     */
    int getPlayerScore();

    /**
     * @return the high score.
     */
    int getHighScoreValue();

    /**
     * 
     * @param point to add to the total.
     */
    void addPoint(int point);

    /**
     * Add the end game bonus to the total score.
     */
    void addBonus();

    /**
     * Returns the multiplier to its initial state.
     */
    void flatMultiplier();

    /**
     * Increase the multiplier.
     */
    void incMultiplier();

    /**
     * Decreases the player's lives.
     */
    void decLives();

    /**
     * Increases the player's lives.
     */
    void incLives();

    /**
     * 
     * @return current number of lives
     */
    int getLives();

    /**
     * @return the board
     */
    GameBoard getBoard();

    /**
     * 
     * @return the current game phase
     */
    GamePhase getPhase();

    /**
     * 
     * @param phase set the game phase
     */
    void setPhase(GamePhase phase);

    /**
     * 
     * @return the current player
     */
    Player getPlayer();

    /**
     * DA CONTROLLARE SE VA BENE O BISOGNA IMPLEMENTARE UNA CLASSE SCORE.
     * @return the topScore of the player who is playing
     */
    Player getTopScores();

    /**
     * 
     * @return the level where the game is taking place
     */
    Level getLevel();

    /**
     * 
     * @return the difficulty of the level returns.
     */
    Difficulty getDifficulty();

    /**
     * 
     * @return if the music is active
     */
    boolean isMusicActive();

    /**
     * 
     * @return if the effects is active
     */
    boolean isEffectActive();
}
