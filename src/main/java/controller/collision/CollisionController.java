package controller.collision;

import java.util.Optional;

import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameObject;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.utilities.Boundaries;
import model.utilities.Pair;

public interface CollisionController {

    /**
     * Check for collisions between the ball and the brick.
     * @param ball the object that can collide with the brick
     * @param brick the brick to check the collision
     * @return a pair consisting of the hit brick and the side on which it was hit
     */
    Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(Ball ball, Brick brick);

    /**
     * Check for collisions between the object and the wall.
     * @param obj the object that can collide with the border
     * @return the side of the collision
     */
    Optional<Boundaries> checkGameObjCollisionsWithWall(GameObject obj);

    /**
     * Check for collisions between the ball and the paddle.
     * @param paddle entity to check
     * @param ball entity to check
     * @return the side of the collision
     */
    Optional<Boundaries> checkBallCollisionsWithPaddle(Ball ball, Paddle paddle);

    /**
     * Check for collisions between the pwup and the paddle.
     * @param pwup entity to check
     * @param paddle entity to check
     * @return a pair consisting of the type of powerUp and the side it was hit on
     */
    Optional<Pair<PowerUp, Boundaries>> checkPwUpCollisionWithPaddle(PowerUp pwup, Paddle paddle);

} 
