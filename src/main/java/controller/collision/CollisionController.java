package controller.collision;

import java.util.Optional;

import model.entities.Ball;
import model.entities.BrickImpl;
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
    Optional<Pair<BrickImpl, Boundaries>> checkBallCollisionsWithBrick(BrickImpl brick, Ball ball);
    /**
     *
     * @param wall
     * @param ball
     * @return collision
     */
    Optional<Boundaries> checkBallCollisionsWithWall(BrickImpl wall,Ball ball);
    /**
     * 
     * @param wall
     * @param ball
     * @return collision
     */
    Optional<Boundaries> checkBallCollisionsWithPaddle(BrickImpl wall,Ball ball);
    /**
     * 
     * @param wall
     * @param paddle
     * @return collision
     */
    Optional<Boundaries> checkWallCollisionsWithPaddle(BrickImpl wall,Paddle paddle);
} 
