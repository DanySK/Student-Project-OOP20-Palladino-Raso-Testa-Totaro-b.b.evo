package model.entities;

import model.utilities.BrickStatus;
import model.utilities.Position;
import model.utilities.PowerUpType;

public class PowerUp extends Brick {
    private PowerUpType pwtype;

    public PowerUp(final Position pos, final double speed, final int height, final int width, final int durability, final BrickStatus brickStatus) {
        super(pos, speed, height, width, durability, brickStatus);
        this.pwtype = PowerUpType.randomPowerUpType();
    }

    /**
     * drops the {@link PowerUp} when the {@link BrickStatus} is set on DROP_POWERUP.
     */
    public void dropPowerUp() {

        //richiama la view per cambiare la grafica del brick(?)

        //inizia a cadere verso il basso

        //se tocca il paddle attiva il powerup e distrugge il gameobj 

        //se tocca il fondo non attiva il pwrup e distrugge il gameobj
    }

    /**
     * activates the powerup when its boundaries hit the paddle.
     * @param seconds
     */
    public void activatePowerUp(final int seconds) {
        //attiva il powerup
        //aspetta X secondi 
        //disattiva il powerUp
    }

    /**
     * getter for {@link PowerUpType} attribute.
     * @return poweruptype
     */
    public PowerUpType getPowerUpType() {
        return this.pwtype;
    }
}
