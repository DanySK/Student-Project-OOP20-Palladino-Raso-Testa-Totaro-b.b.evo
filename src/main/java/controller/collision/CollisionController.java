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
     * 
     * @param brick
     * @param ball
     * @return collision
     */
    Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(Ball ball, Brick brick);
    /**
     *
     * @param wall
     * @param ball
     * @return collision
     */
    Optional<Boundaries> checkGameObjCollisionsWithWall( GameObject obj);
    /**
     * 
     * @param paddle
     * @param ball
     * @return collision
     */
    Optional<Boundaries> checkBallCollisionsWithPaddle(Ball ball, Paddle paddle);

    /**
     * 
     * @param pwup
     * @param paddle
     * @return collision
     */
    Optional<Pair<PowerUp, Boundaries>> checkPwUpCollisionWithPaddle(PowerUp pwup, Paddle paddle);

} 
