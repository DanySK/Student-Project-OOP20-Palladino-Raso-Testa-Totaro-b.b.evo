package controller.collision;

import java.util.Optional;

import model.entities.Ball;
import model.entities.BrickImpl;
import model.entities.Paddle;
import model.utilities.Boundaries;
import model.utilities.Pair;

public class CollisionControllerImpl implements CollisionController {

    public CollisionControllerImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Pair<BrickImpl, Boundaries>> checkBallCollisionsWithBrick(BrickImpl brick, Ball ball) {
        return null;
    }

    @Override
    public Optional<Boundaries> checkBallCollisionsWithWall(BrickImpl wall, Ball ball) {
        return null;
    }

    @Override
    public Optional<Boundaries> checkBallCollisionsWithPaddle(BrickImpl wall, Ball ball) {
        return null;
    }

    @Override
    public Optional<Boundaries> checkWallCollisionsWithPaddle(BrickImpl wall, Paddle paddle) {
        return null;
    }

    private Boundaries checkCollisions(final double ballPos, final double gameObjPos, Boundaries bounds) {
        Boundaries checkedBounds = null;
        switch (bounds) {
        case LOWER:
            if (ballPos < gameObjPos) {
                checkedBounds = Boundaries.LOWER;
            }
            break;
        case SIDE_LEFT:
            if (ballPos < gameObjPos) { 
                checkedBounds = Boundaries.SIDE_LEFT; 
                }
            break;
        case SIDE_RIGHT:
            if (ballPos > gameObjPos) {
                checkedBounds = Boundaries.SIDE_RIGHT;
            }
            break;
        case UPPER:
            if (ballPos > gameObjPos) {
                checkedBounds = Boundaries.UPPER;
            }
            break;
        default:
            break;
        }
        return checkedBounds;
    }
}
