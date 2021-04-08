package controller.collision;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.entities.Ball;
import model.entities.BrickImpl;
import model.entities.GameObject;
import model.entities.Paddle;
import model.utilities.Boundaries;
import model.utilities.Pair;
import paranoid.model.collision.Collision;

public class CollisionControllerImpl implements CollisionController {

    private final Map<Boundaries, Boolean> collision = new HashMap<>();
    /**
     * 
     */
    @Override
    public Optional<Pair<BrickImpl, Boundaries>> checkBallCollisionsWithBrick(final BrickImpl brick, final Ball ball) {
        collision.put(Boundaries.SIDE_LEFT, checkCollisions(ballX(ball), brickX(brick) + brickWidth(brick), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.SIDE_RIGHT, checkCollisions(ballX(ball) + ballWidth(ball), brickX(brick), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.LOWER, checkCollisions(ballY(ball) + ballHeight(ball), brickY(brick), Boundaries.LOWER));
        collision.put(Boundaries.UPPER, checkCollisions(ballY(ball), brickY(brick) + brickHeight(brick), Boundaries.UPPER));

        collision.forEach((k, v) -> {
            if (!v.booleanValue()) {
                brick.getHitBall().put(ball, k);
            }
        });
        return Optional.ofNullable(brick.getHitBall().get(ball));
    }

    /**
     * 
     */
    @Override
    public Optional<Boundaries> checkBallCollisionsWithWall(final BrickImpl wall, final Ball ball) {
        return null;
    }

    /**
     * 
     */
    @Override
    public Optional<Boundaries> checkBallCollisionsWithPaddle(final Ball ball, final Paddle paddle) {
        collision.put(Boundaries.SIDE_LEFT, checkCollisions(ballX(ball), paddleX(paddle) + paddleWidth(paddle), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.SIDE_RIGHT, checkCollisions(ballX(ball) + ballWidth(ball), paddleX(paddle), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.LOWER, checkCollisions(ballY(ball) + ballHeight(ball), paddleY(paddle), Boundaries.LOWER));
        collision.put(Boundaries.UPPER, checkCollisions(ballY(ball), paddleY(paddle) + paddleHeight(paddle), Boundaries.UPPER));

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
    public Optional<Boundaries> checkPaddleCollisionsWithWall(final BrickImpl wall, final Paddle paddle) {
        //collision.put(Boundaries.SIDE_LEFT, checkCollisions(paddleX(paddle), brickX(brick) + brickWidth(brick), Boundaries.SIDE_RIGHT));
        //collision.put(Boundaries.SIDE_RIGHT, checkCollisions(ballX(ball) + ballWidth(ball), brickX(brick), Boundaries.SIDE_RIGHT));
        return null;
    }

    private Map<Boundaries, Boolean> fillMap(GameObject objOne, final int objTwo){
        collision.put(Boundaries.SIDE_LEFT, checkCollisions(ballX(ball), paddleX(paddle) + paddleWidth(paddle), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.SIDE_RIGHT, checkCollisions(ballX(ball) + ballWidth(ball), paddleX(paddle), Boundaries.SIDE_RIGHT));
        collision.put(Boundaries.LOWER, checkCollisions(ballY(ball) + ballHeight(ball), paddleY(paddle), Boundaries.LOWER));
        collision.put(Boundaries.UPPER, checkCollisions(ballY(ball), paddleY(paddle) + paddleHeight(paddle), Boundaries.UPPER));
    }

    private int paddleHeight(final Paddle paddle) {
        return paddle.getHeight();
    }

    private int paddleWidth(final Paddle paddle) {
        return paddle.getWidth();
    }

    private int paddleY(final Paddle paddle) {
        return paddle.getPos().getY();
    }

    private int ballWidth(final Ball ball) {
        return ball.getWidth();
    }

    private int paddleX(final Paddle paddle) {
        return paddle.getPos().getX();
    }

    private int ballHeight(final Ball ball) {
        return ball.getHeight();
    }

    private int ballY(final Ball ball) {
        return ball.getPos().getY();
    }

    private int ballX(final Ball ball) {
        return ball.getPos().getX();
    }

    private int brickHeight(final BrickImpl brick) {
        return brick.getHeight();
    }

    private int brickY(final BrickImpl brick) {
        return brick.getPos().getY();
    }

    private int brickWidth(final BrickImpl brick) {
        return brick.getWidth();
    }

    private int brickX(final BrickImpl brick) {
        return brick.getPos().getX();
    }

    private Boolean checkCollisions(final double ballPos, final double gameObjPos, final Boundaries bounds) {
        switch (bounds) {
        case LOWER:
            break;
        case SIDE_LEFT:
            break;
        case SIDE_RIGHT:
            break;
        case UPPER:
            break;
        default:
            break;
        }

        return null;
    }
}
