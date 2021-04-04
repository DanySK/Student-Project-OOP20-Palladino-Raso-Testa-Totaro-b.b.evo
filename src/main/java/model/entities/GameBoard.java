package model.entities;

import java.util.Collection;
import java.util.Set;

/**
 * Interface that instantiates methods for creating the game board.
 *
 */
public interface GameBoard extends GameBoardEventListeners{

    /**
     * 
     * @param balls to set to the gameboard
     */
    void setBalls(Collection<Ball> balls);

    /**
     * 
     * @param ball to add to the gameboard.
     */
    void addBall(Ball ball);

    /**
     * 
     * @param bricks added in the gameboard
     */
    void setBricks(Collection<Brick> bricks);

    /**
     * 
     * @return all the balls in the game
     */
    Set<Ball> getBalls();

    /**
     * 
     * @return all the bricks in the gameboard
     */
    Set<Brick> getBricks();

    /**
     * 
     * @return the border of the gameboard
     */
    Border getBorder();

    /**
     * 
     * @param ball to remove
     */
    void removeBall(Ball ball);

    /**
     * 
     * @param brick to remove
     */
    void removeBrick(Brick brick);
}
