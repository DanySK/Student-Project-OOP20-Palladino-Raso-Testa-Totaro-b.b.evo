package model.entities;

import model.utilities.Position;
import model.utilities.PowerUpType;

public class PowerUpImpl extends BrickImpl implements PowerUp {
    private PowerUpType pwtype;

    public PowerUpImpl(Position pos, double speed, int height, int width, int durability) {
        super(pos, speed, height, width, durability);
        this.pwtype = PowerUpType.randomPowerUpType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dropPowerUp() {

        //richiama la view per cambiare la grafica del brick(?)

        //inizia a cadere verso il basso

        //se tocca il paddle attiva il powerup e distrugge il gameobj 

        //se tocca il fondo non attiva il pwrup e distrugge il gameobj
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activatePowerUp(final int seconds) {
        //attiva il powerup
        //aspetta X secondi 
        //disattiva il powerUp
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUpType getPowerUpType() {
        return this.pwtype;
    }
}
