package model.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import controller.collision.CollisionController;
import controller.collision.CollisionControllerImpl;
import controller.event.Event;
import controller.event.EventHandler;
import controller.game.GameState;
import controller.input.ControllerInput;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.Pair;
import model.utilities.Wall;


public class GameBoardImpl implements GameBoard {

    private final Set<Ball> balls; // da guardare perche abbiamo solo una pallina
    private final Set<Brick> bricks;
    private Optional<Paddle> paddle; //dubbio qui
    private final Wall wall;
    private final EventHandler eventHandler;
    private final CollisionController collision;

    public GameBoardImpl(final Wall wall, final GameState state) {
        this.balls = new HashSet<>();
        this.bricks = new HashSet<>();
        this.paddle = Optional.empty(); //dubbio qui
        this.wall = wall;
        this.eventHandler = new EventHandler(state);
        this.collision = new CollisionControllerImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eventListener(final Event e) {
        this.eventHandler.addEvent(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBalls(final Collection<Ball> balls) {
        this.balls.clear();
        this.balls.addAll(balls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBall(final Ball ball) {
        this.addBall(ball);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBricks(final Collection<Brick> bricks) {
        this.bricks.clear();
        this.bricks.addAll(bricks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaddle(final Paddle paddle) {
        //dubbio qui
        this.paddle = Optional.of(paddle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ball> getBalls() {
        return Collections.unmodifiableSet(this.balls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Paddle getPaddle() {
        return this.paddle.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wall getWall() {
        return this.wall;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBall(final Ball ball) {
        this.balls.remove(ball);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBrick(final Brick brick) {
        this.bricks.remove(brick);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Boundaries> checkBallCollisionsWithWall(final Ball ball) {
        return this.collision.checkBallCollisionsWithWall(this.wall, ball);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Boundaries> checkPaddleCollisionsWithWall(final Paddle paddle) {
        return this.collision.checkPaddleCollisionsWithWall(this.wall, paddle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(final Ball ball) {
        Optional<Pair<Brick, Boundaries>> result = Optional.empty();
        for (final var b : this.bricks) {
            result = this.collision.checkBallCollisionsWithBrick(ball, b);
            if (!result.isEmpty()) {
                return result;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Optional<Boundaries>, Optional<Angle>> checkBallCollisionsWithPaddle(final Ball ball, final Paddle paddle) {
        Optional<Boundaries> result = Optional.empty();
        result = this.collision.checkBallCollisionsWithPaddle(ball, paddle);
        if (result.get().equals(Boundaries.UPPER)) {
            final double centerBall = ball.getPos().getX() + (ball.getWidth() / 2);
            final double zonePlayer = paddle.getWidth() / Angle.values().length;
            for (int i = 0; i < Angle.values().length; i++) {
                if (centerBall > paddle.getPos().getX() + (i * zonePlayer) 
                        && centerBall < paddle.getPos().getX() + ((i + 1) * zonePlayer)) {
                   return new Pair<>(Optional.of(result.get()), Optional.of(Angle.values()[i]));
                }
            }
        }
        return new Pair<>(Optional.empty(), Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getSceneEntities() {
        final Set<GameObject> entities = new HashSet<>();
        entities.addAll(this.balls);
        entities.addAll(this.bricks);
        entities.addAll(this.paddle); // bisogna modificare il paddle perche non accetta optional
        return Collections.unmodifiableSet(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final int timeElapsed) {
        this.getSceneEntities().forEach(i -> i.updatePhysics(timeElapsed, this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final ControllerInput inputController) {
        this.paddle.get().updateInput(inputController); //qui non so se va bene
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventHandler getEventHanlder() {
        return this.eventHandler;
    }
}
