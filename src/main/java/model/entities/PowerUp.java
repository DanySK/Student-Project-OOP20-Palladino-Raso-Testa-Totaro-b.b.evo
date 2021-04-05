package model.entities;

import model.utilities.PowerUpType;

public interface PowerUp extends Brick {
    /**
     * drops the {@link PowerUp} when the {@link Status} is set on DROP_POWERUP.
     */
    void dropPowerUp();
    /**
     * getter for {@link PowerUpType} attribute.
     * @return pwtype
     */
    PowerUpType getPowerUpType();

    /**
     * activates the powerup when its boundaries hit the paddle.
     * @param seconds
     */
    void activatePowerUp(int seconds);
}
