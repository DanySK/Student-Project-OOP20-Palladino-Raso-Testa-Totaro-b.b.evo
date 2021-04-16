package controller.collision;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameObject;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.entities.Wall;
import model.utilities.Boundaries;
import model.utilities.Pair;

/**
 * collision implementation.
 */
public class CollisionControllerImpl implements CollisionController {

    private final Map<Boundaries, Boolean> collision = new HashMap<>();
    private Boundaries side;
    private boolean isCollision;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Boundaries> checkGameObjCollisionsWithWall(final Wall wall, final GameObject obj) {
        this.side = null;
        collision.put(Boundaries.SIDE_LEFT, checkCollisions(objX(obj), wall.getUpperLeftCorner().getX(), Boundaries.SIDE_LEFT));
        collision.put(Boundaries.SIDE_RIGHT, checkCollisions(objX(obj) + objWidth(obj), wall.getRightBottomCorner().getX(), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.LOWER, checkCollisions(objY(obj) + objHeight(obj), wall.getRightBottomCorner().getY(), Boundaries.LOWER));
        collision.put(Boundaries.UPPER, checkCollisions(objY(obj), wall.getUpperLeftCorner().getY(), Boundaries.UPPER));
        collision.forEach((k, v) -> {
            if (v.booleanValue()) {
                side = k;
            }
        });
        return Optional.ofNullable(this.side);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(final Ball ball, final Brick brick) {
        this.isCollision = false;
        this.fillMap(brick, ball);

        for (final Entry<Boundaries, Boolean> entry : this.collision.entrySet()) {
            if (!entry.getValue() && !this.isCollision) {
                brick.getHit().put(ball, entry.getKey());
                this.isCollision = true;
            }
        }

        if (this.isCollision) {
            return Optional.empty();
        }
        return Optional.of(new Pair<>(brick, brick.getHit().get(ball)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Boundaries> checkBallCollisionsWithPaddle(final Ball ball, final Paddle paddle) {
        this.isCollision = false;
        this.fillMap(paddle, ball);
        this.collision.forEach((k, v) -> {
            if (!v.booleanValue() && !this.isCollision) {
                paddle.getHit().put(ball, k);
                this.isCollision = true;
            }
        });
       if (this.isCollision) {
           return Optional.empty();
       }
        return Optional.of(paddle.getHit().get(ball));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<PowerUp, Boundaries>> checkPwUpCollisionWithPaddle(final PowerUp pwup, final Paddle paddle) {
        this.isCollision = false;
        this.fillMap(paddle, pwup);
        this.collision.forEach((k, v) -> {
            if (!v.booleanValue() && !this.isCollision) {
                pwup.getHit().put(paddle, k);
                this.isCollision = true;
            }
        });
        if (this.isCollision) {
            return Optional.empty();
        }
        return Optional.ofNullable(new Pair<>(pwup, pwup.getHit().get(paddle)));
    }

    /**
     * 
     * @param obj1
     * @param obj2
     */
    private void fillMap(final GameObject obj1, final GameObject obj2) {
        this.collision.put(Boundaries.SIDE_RIGHT, checkCollisions(objX(obj2), objX(obj1) + objWidth(obj1), Boundaries.SIDE_LEFT));
        this.collision.put(Boundaries.SIDE_LEFT, checkCollisions(objX(obj2) + objWidth(obj2), objX(obj1), Boundaries.SIDE_RIGHT));
        this.collision.put(Boundaries.UPPER, checkCollisions(objY(obj2) + objHeight(obj2), objY(obj1), Boundaries.LOWER));
        this.collision.put(Boundaries.LOWER, checkCollisions(objY(obj2), objY(obj1) + objHeight(obj1), Boundaries.UPPER));
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
        switch (bounds) {
        case UPPER: 
            return obj1 < obj2;
        case SIDE_LEFT:
            return obj1 < obj2;
        case LOWER: 
            return obj1 > obj2;
        case SIDE_RIGHT: 
            return obj1 > obj2;
        default: return false;
        }
    }
}
