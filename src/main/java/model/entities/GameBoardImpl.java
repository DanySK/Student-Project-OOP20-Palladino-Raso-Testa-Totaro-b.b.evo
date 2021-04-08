package model.entities;

import java.util.Collection;
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


public class GameBoardImpl implements GameBoard {

    private final Set<Ball> balls;
    private final Set<Brick> bricks;
    private final Optional<Paddle> paddle;
    private final Wall wall;
    private final EventHandler eventHandler;
    private final CollisionController collision;

    public GameBoardImpl(final Wall wall, final GameState state) {
        this.balls = new HashSet<>();
        this.bricks = new HashSet<>();
        this.paddle = Optional.empty();
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

    @Override
    public void setBalls(Collection<Ball> balls) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addBall(Ball ball) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setBricks(Collection<Brick> bricks) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPlayers(Paddle paddle) {
        this.paddle = Optional.of(paddle);
    }

    @Override
    public Set<Ball> getBalls() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Brick> getBricks() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Paddle getPaddle() {
        return this.paddle.get();
    }

    @Override
    public Border getBorder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeBall(Ball ball) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeBrick(Brick brick) {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateState(int timeElapsed) {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final ControllerInput inputController) {
        this.paddle.get().updateInput(inputController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventHandler getEventHanlder() {
        return this.eventHandler;
    }
}
