package controller.collision;

import java.util.Optional;

import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
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
    Optional<Boundaries> checkBallCollisionsWithWall(Brick wall, Ball ball);
    /**
     * 
     * @param paddle
     * @param ball
     * @return collision
     */
    Optional<Boundaries> checkBallCollisionsWithPaddle(Ball ball, Paddle paddle);
    /**
     * 
     * @param wall
     * @param paddle
     * @return collision
     */
    Optional<Boundaries> checkPaddleCollisionsWithWall(Brick wall, Paddle paddle);
} 
