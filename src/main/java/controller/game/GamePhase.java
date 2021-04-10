package controller.game;

/**
 * Represent all the possible state during the game.
 *
 */
public enum GamePhase {

    /**
     * Initial phase of entity creation.
     */
    START,

    /**
     * Run the game with gameloop cycle.
     */
    RUNNING,

    /**
     * When you want to take a break all the object on the map will be freeze.
     */
    PAUSE,

    /**
     * When you finish the level you can pass to next level or finish the game.
     */
    WIN,

    /**
     * When your lifes are ends the game is finish and you can enter your alias to see your ranking.
     */
    LOST,

    /**
     * Pre-game where you can navigate to see more info like settings and ranking.
     */
    MENU;
}
