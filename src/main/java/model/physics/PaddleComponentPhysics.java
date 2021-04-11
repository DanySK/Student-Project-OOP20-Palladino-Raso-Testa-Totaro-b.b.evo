package model.physics;

import model.entities.GameBoardImpl;
import model.entities.GameObject;
import model.entities.Paddle;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.GameUtilities;
import model.utilities.Position;

public class PaddleComponentPhysics implements ComponentPhysics {

    /**
     * 
     */
    @Override
    public void update(final int timeElapsed, final GameObject gameObject, final GameBoardImpl world) {
        final Position upperLeftCorner = GameUtilities.getUpperLeftCorner();
        final Position bottomRightCorner = GameUtilities.getRightBottomCorner();

        final Paddle paddle = (Paddle) gameObject;
        final Position posPaddle = paddle.getPos();
        final DirVector dirVectPaddle = paddle.getDirVector();
        paddle.setPos(posPaddle.sum(dirVectPaddle.mul(COST_VELOCITY * timeElapsed * paddle.getSpeed())));
        if (world.checkPaddleCollisionsWithWall(paddle).isPresent()) {
            if (world.checkPaddleCollisionsWithWall(paddle).get().equals(Boundaries.SIDE_LEFT)) {
                paddle.setPos(new Position(upperLeftCorner.getX(), paddle.getPos().getY()));
            } else {
                paddle.setPos(new Position(bottomRightCorner.getX() - paddle.getWidth(), paddle.getPos().getY()));
            }
        }
    }

}
