package model.entities;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import controller.event.EventHandler;
import controller.event.GameBoardEventListeners;
import controller.input.ControllerInput;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.Pair;

/**
 * Interface that instantiates methods for creating the game board.
 *
 */
public interface GameBoard extends GameBoardEventListeners {

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
     * @param powerup to set to the gameboard
     */
    void setPowerUps(Collection<PowerUp> pwup);

    /**
     * 
     * @param powerup to add to the gameboard.
     */
    void addPowerUp(PowerUp pwup);

    /**
     * 
     * @param bricks added in the gameboard
     */
    void setBricks(Collection<Brick> bricks);

    /**
     * 
     * @param paddle to add to the world
     */
    void setPaddle(Paddle paddle);

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
     * @return all the powerups in the gameboard
     */
    Set<PowerUp> getPowerUp();


    /**
     * 
     * @return the border of the gameboard
     */
    Wall getWall();

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

    /**
     * 
     * @param pwup to remove
     */
    void removePowerUp(PowerUp pwup);

    /**
     * the world asks the collision manager to check 
     * if there have been collisions between wall and object.
     * @param obj the object to be checked
     * @return on what surface the object collides
     */
    Optional<Boundaries> checkGameObjCollisionsWithWall(GameObject obj);

    /**
     * the world asks the collision manager to check 
     * if there have been collisions between ball and bricks.
     * @param ball object that can collide
     * @return Pair of brick and border
     */
    Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(Ball ball);

    /**
     * the world asks the collision manager to check 
     * if there have been collisions between ball and the paddle.
     * @param ball object that can collide
     * @return if a collision has occurred in the upper part of the player, 
     * the direction the ball will take is also calculated. 
     */
    Pair<Optional<Boundaries>, Optional<Angle>> checkBallCollisionsWithPaddle(Ball ball);

    /**
     * the world asks the collision manager to check 
     * if there have been collisions between powerUp and bricks.
     * @param pwUp object that can collide
     * @return Pair of pwUp and border
     */
    Optional<Pair<PowerUp, Boundaries>> checkPowerUpCollisionsWithPaddle(PowerUp pwUp);

    /**
     * 
     * @return a set of all the gameObj in the world
     */
    Set<GameObject> getSceneEntities();

    /**
     * 
     * @param d the time difference delta time
     */
    void updateState(double d);

    /**
     * Update paddle input component.
     * @param inputController controller that check the key pressed by user
     */
    void movePlayer(ControllerInput inputController);

    /**
     * @return the eventHandler 
     */
    EventHandler getEventHanlder();

}
