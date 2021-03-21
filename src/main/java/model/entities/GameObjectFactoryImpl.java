package model.entities;

import model.utilities.Position;

public class GameObjectFactoryImpl implements GameObjectFactory  {

    private static final int BRICKWIDTH = 26;
    private static final int BRICKHEIGHT = 26;
    private static final int WALLWIDTH = 30;
    private static final int WALLHEIGHT = 30;
    private static final int POWERUPWIDTH = 23;
    private static final int POWERUPHEIGHT = 23;
    private static final int POWERUPSPEED = 2;
    private static final int PADDLEWIDTH = 30;
    private static final int PADDLEHEIGHT = 30;
    private static final int BALLWIDTH = 30;
    private static final int BALLHEIGHT = 30;
    private static final int BALLSPEED = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createBrick(final Position position) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createPowerUp(final Position position) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createPaddle(final Position position) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createWall(final Position position) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createBall(final Position position) {
        return null;
    }

}
