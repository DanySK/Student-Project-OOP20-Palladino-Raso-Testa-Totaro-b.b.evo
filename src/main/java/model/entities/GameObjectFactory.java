package model.entities;

import model.utilities.Position;

/**
 * An interface for creates {@link GameObject}. 
 */
public interface GameObjectFactory {
    /**
     * Used to build a brick.
     * 
     * @param position the position of the brick
     * @return the brick
     */
    GameObject createBrick(Position position);
    /**
     * Used to build a powerUp.
     * 
     * @param position the position of the powerUp
     * @return the powerUp
     */
    GameObject createPowerUp(Position position);
    /**
     * Used to build a paddle.
     * 
     * @param position the position of the paddle
     * @return the paddle
     */
    Movable createPaddle(Position position);
    /**
     * Used to build a wall.
     * 
     * @param position the position of the wall
     * @return the wall
     */
    GameObject createWall(Position position);

    /**
     * Used to build a Blinky ghost.
     * 
     * @param position the position of the ghost
     * @return the ghost
     */
    Movable createBall(Position position);
}
