package model.entities;

import controller.physics.ComponentPhysics;
import model.utilities.Position;
import model.utilities.PowerUpType;
import model.utilities.Status;
import model.utilities.DirVector;
import view.graphics.ComponentGraphics;

public class PowerUpImpl extends BrickImpl implements PowerUp {

    private PowerUpType pwtype;

    public PowerUpImpl(Position pos, DirVector vel, double speed, int height, int width, ComponentPhysics physics,
            ComponentGraphics graphics) {
        super(pos, vel, speed, height, width, physics, graphics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
        if (this.status == Status.DESTR && this.durability <= 0) {
            this.status = Status.DROP_POWERUP;
            dropPowerUp();
            return this.status;
        }
        return this.status;
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
    }

    @Override
    public PowerUpType getPowerUpType() {
        return this.pwtype;
    }


}
