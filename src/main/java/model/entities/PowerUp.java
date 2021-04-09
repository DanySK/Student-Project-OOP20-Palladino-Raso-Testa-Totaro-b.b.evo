package model.entities;

import model.utilities.GameObjStatus;
import model.utilities.Position;
import model.utilities.PowerUpType;

public class PowerUp extends Brick {
    private PowerUpType pwtype;

    public PowerUp(final Position pos, final double speed, final int height, final int width, final int durability, final GameObjStatus gameObjStatus) {
        super(pos, speed, height, width, durability, gameObjStatus);
        this.pwtype = PowerUpType.randomPowerUpType();
    }

    /**
     * drops the {@link PowerUp} when the {@link GameObjStatus} is set on DROP_POWERUP.
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
        
        switch (this.pwtype) {
        case DAMAGE_DOWN:
            break;
        case DAMAGE_UP:
            break;
        case LIFE_DOWN:
            break;
        case LIFE_UP:
            break;
        case SPEED_UP:
            break;
        case SPEED_DOWN:
            break;
        default:
            break;
        }
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
