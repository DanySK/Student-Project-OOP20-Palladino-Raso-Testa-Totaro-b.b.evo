package model.physics;

import java.util.Optional;

import controller.event.HitEvent;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameBoardImpl;
import model.entities.GameObject;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.Pair;
import model.utilities.Position;

public class BallComponentPhysics implements ComponentPhysics {

    /**
     * 
     */
    @Override
    public void update(final int timeElapsed, final GameObject gameObject, final GameBoardImpl world) {
        final Ball ball = (Ball) gameObject;
        final Position posBall = ball.getPos();
        final DirVector dirVectBall = ball.getDirVector();

        ball.setPos(ball.getPos().sum(dirVectBall.mul(COST_VELOCITY * timeElapsed * ball.getSpeed())));

        final Optional<Boundaries> wallCollisionInfo = world.checkBallCollisionsWithWall(ball);
        if (wallCollisionInfo.isPresent()) {
            ball.setPos(posBall);
            world.eventListener(new HitEvent(ball, wallCollisionInfo.get()));
            if (wallCollisionInfo.get().equals(Boundaries.SIDE_LEFT) 
                    || wallCollisionInfo.get().equals(Boundaries.SIDE_RIGHT)) {
                ball.setDirOnX();
            } else {
                ball.setDirOnY();
            }
        }

        final Optional<Pair<Brick, Boundaries>> brickCollisionInfo = world.checkBallCollisionsWithBrick(ball);
        if (brickCollisionInfo.isPresent()) {
            ball.setPos(posBall);
            world.eventListener(new HitEvent(brickCollisionInfo.get().getX(), brickCollisionInfo.get().getY()));
            if (brickCollisionInfo.get().getY().equals(Boundaries.SIDE_LEFT) 
                    || brickCollisionInfo.get().getY().equals(Boundaries.SIDE_RIGHT)) {
                ball.setDirOnX();
            } else {
                ball.setDirOnY();
            }
        }
        
        final Pair<Optional<Boundaries>, Optional<Angle>> paddleCollisionInfo = world.checkBallCollisionsWithPaddle(ball, paddle);
    }

}
