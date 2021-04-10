
package controller.collision;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameObject;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.utilities.Boundaries;
import model.utilities.GameUtilities;
import model.utilities.Pair;
import model.utilities.Position;
import paranoid.model.collision.Collision;

/**
 * collision implementation.
 */
public class CollisionControllerImpl implements CollisionController {
    private final Position upperLeftCorner = GameUtilities.getUpperLeftCorner();
    private final Position bottomRightCorner = GameUtilities.getRightBottomCorner();
    private final Map<Boundaries, Boolean> collision = new HashMap<>();

    /**
     * 
     */
    @Override
    public Optional<Boundaries> checkGameObjCollisionsWithWall(GameObject obj) {
        
    }
    
    /**
     * Check if there has been a collision between the ball and the brick. If yes then it returns a Pair of bricks and border
     */
    @Override
    public Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(final Ball ball, final Brick brick) {
        this.fillMap(ball, brick);
        collision.forEach((k, v) -> {
            if (!v.booleanValue()) {
                brick.getHitBall().put(ball, k);
            }
        });
        return Optional.ofNullable(new Pair<>(brick, brick.getHitBall().get(ball)));
    }


    /**
     * Check if there has been a collision between the ball and the paddle. If yes then return the border
     */
    @Override
    public Optional<Boundaries> checkBallCollisionsWithPaddle(final Ball ball, final Paddle paddle) {
        this.fillMap(ball, paddle);
        collision.forEach((k, v) -> {
            if (!v.booleanValue()) {
                paddle.getHitBall().put(ball, k);
            }
        });
        return Optional.ofNullable(paddle.getHitBall().get(ball));
    }

    /**
     * 
     */
    @Override
    public Optional<Pair<PowerUp, Boundaries>> checkPwUpCollisionWithPaddle(final PowerUp pwup, final Paddle paddle) {
        this.fillMap(pwup, paddle);
        collision.forEach((k, v) -> {
            if (!v.booleanValue()) {
                paddle.getHitBall().put(pwup, k);
            }
        });
        return null;
    }

    private Map<Boundaries, Boolean> fillMap(final GameObject obj1, final GameObject obj2) {
        collision.put(Boundaries.SIDE_LEFT, checkCollisions(objX(obj1), objX(obj2) + objWidth(obj2), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.SIDE_RIGHT, checkCollisions(objX(obj1) + objWidth(obj1), objX(obj2), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.LOWER, checkCollisions(objY(obj1) + objHeight(obj1), objY(obj2), Boundaries.LOWER));
        collision.put(Boundaries.UPPER, checkCollisions(objY(obj1), objY(obj2) + objHeight(obj2), Boundaries.UPPER));
        return collision;
    }

    private int objHeight(final GameObject obj) {
        return obj.getHeight();
    }

    private int objWidth(final GameObject obj) {
        return obj.getWidth();
    }

    private double objX(final GameObject obj) {
        return obj.getPos().getX();
    }

    private double objY(final GameObject obj) {
        return obj.getPos().getY();
    }

    private Boolean checkCollisions(final double obj1, final double obj2, final Boundaries bounds) {
        Boolean checkedBounds = false;
        switch (bounds) {
        case LOWER:
            if (obj1 < obj2) {
                checkedBounds = true;
            }
            break;
        case SIDE_LEFT:
            if (obj1 < obj2) { 
                checkedBounds = true;
                }
            break;
        case SIDE_RIGHT:
            if (obj1 > obj2) {
                checkedBounds = true;
            }
            break;
        case UPPER:
            if (obj1 > obj2) {
                checkedBounds = true;
            }
            break;
        default:
            checkedBounds = false;
            break;
        }
        return checkedBounds;
    }




}
