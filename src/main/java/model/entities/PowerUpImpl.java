package model.entities;

import model.utilities.GameObjectType;
import model.utilities.Position;
import model.utilities.PowerUpType;

public class PowerUpImpl extends BrickImpl implements PowerUp {
    private PowerUpType pwtype;

    public PowerUpImpl(final int width, final int height, final Position position, final GameObjectType type, 
            final int durability) {
        super(width, height, position, type, durability);
    }

    /**
     * 
     */
    @Override
    public Boolean isBroken() {
        if (this.durability <= 0) {
            dropPowerUp();
            return true;
        }
        return false;
    }

    /**
     * 
     */
    @Override
    public void dropPowerUp() {
        //richiama la view per cambiare la grafica del brick(?)

        //inizia a cadere verso il basso

        //se tocca il paddle attiva il powerup e distrugge il gameobj 

        //se tocca il fondo non attiva il pwrup e distrugge il gameobj

    }

    /**
     * 
     */
    @Override
    public void activatePowerUp(final int seconds) {
        //attiva il powerup

    }


}
