package model.entities;

import model.utilities.Direction;
import model.utilities.GameObjectType;
import model.utilities.Position;
import model.utilities.Status;

/**
 * An implementation of {@link GameObjectFactory}.
 */
public class GameObjectFactoryImpl implements GameObjectFactory  {

    private static final int BRICKWIDTH = 26;
    private static final int BRICKHEIGHT = 26;
    private static final int WALLWIDTH = 30;
    private static final int WALLHEIGHT = 30;
    private static final int POWERUPWIDTH = 26;
    private static final int POWERUPHEIGHT = 26;
    private static final int POWERUPSPEED = 2;
    private static final int PADDLEWIDTH = 25;
    private static final int PADDLEHEIGHT = 25;
    private static final int PADDLESPEED = 1;
    private static final int BALLWIDTH = 30;
    private static final int BALLHEIGHT = 30;
    private static final int BALLSPEED = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createBrick(final Position position) {
        return new BrickImpl(BRICKWIDTH, BRICKHEIGHT, position, GameObjectType.BRICK, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createPowerUp(final Position position) {
        return new PowerUpImpl(POWERUPWIDTH, POWERUPHEIGHT, position, GameObjectType.POWERUP, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createPaddle(final Position position) {
        return new MovableGameObj(PADDLEWIDTH, PADDLEHEIGHT, position, GameObjectType.PADDLE, Direction.LEFT, 
                Status.NOT_DESTR, PADDLESPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createWall(final Position position) {
        return new BrickImpl(WALLWIDTH, WALLHEIGHT, position, GameObjectType.WALL, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createBall(final Position position) {
        return new MovableGameObj(BALLWIDTH, BALLHEIGHT, position, GameObjectType.BALL, Direction.UP, 
                Status.DESTR, BALLSPEED);
    }

}
