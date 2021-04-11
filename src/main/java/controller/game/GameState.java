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
     * 
     * @return score values
     */
    int getPlayerScore();

    /**
     * Set brick value score in base of difficulty selected.
     */
    void baseMultiplier();

    /**
     * 
     * @return current number of lives
     */
    int getLives();

    /**
     * 
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
     * 
     * @return the topScore of the player who is playing
     */
    int getTopScores();

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
