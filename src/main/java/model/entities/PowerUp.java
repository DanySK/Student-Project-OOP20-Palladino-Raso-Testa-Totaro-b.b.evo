
package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ComponentInput;
import controller.input.ComponentInputEmpty;
import controller.input.ControllerInput;
import model.physics.ComponentPhysics;
import model.physics.PwUpComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.GameObjStatus;
import model.utilities.Position;
import model.utilities.PowerUpType;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;

public class PowerUp extends GameObjectImpl {

    private final PowerUpType pwtype;

    private final float activeTime;
    private final float speedModifier;
    private final int lifeModifier;
    private final int damageModifier;

    private String texturePath;

    private final Map<GameObject, Boundaries> hit = new HashMap<>();

    public PowerUp(final Position pos, final DirVector dirVel, final double speed, final int height, final int width,
            final ComponentPhysics physics, final ComponentInput input, final ComponentGraphics graphics,
            final GameObjStatus status, final String texturePath) {
        super(pos, new DirVector(0, -1), speed, height, width, physics, input, graphics, status);
        this.pwtype = PowerUpType.randomPowerUpType();
        this.activeTime = PowerUpType.valueOf(this.pwtype.toString()).getActiveTime();
        this.damageModifier = PowerUpType.valueOf(this.pwtype.toString()).getDamageModifier();
        this.lifeModifier = PowerUpType.valueOf(this.pwtype.toString()).getLifeModifier();
        this.speedModifier = PowerUpType.valueOf(this.pwtype.toString()).getSpeedModifier();
        this.texturePath = texturePath;
    }

    /**
     * 
     * @param ms amount of seconds to wait
     */
    public void waitSeconds(final float ms) {
        try {
            Thread.sleep((long) ms * 1000);
        } catch  (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * getter for {@link PowerUpType} attribute.
     * @return which powerup will be created
     */
    public PowerUpType getPowerUpType() {
        return this.pwtype;
    }

    /**
     * getter for the damage modifier.
     * @return the damage modifier 
     */
    public int getDamageModifier() {
        return damageModifier;
    }

    /**
     * getter for the life modifier. 
     * @return the life modifier
     */
    public int getLifeModifier() {
        return lifeModifier;
    }

    /**
     * getter for the speed modifier.
     * @return the speed modifier
     */
    public float getSpeedModifier() {
        return speedModifier;
    }

    /**
     * getter for the active time.
     * @return active time in seconds
     */
    public float getActiveTime() {
        return activeTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        this.getComponentPhysics().update(timeElapsed, this, world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final ControllerInput controller) {
        this.getComponentInput().update(this, controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) {
        this.getComponentGraphics().update(this, graphicsAdapter);
    }
    
    /**
     * 
     * @return map
     */
    public Map<GameObject, Boundaries> getHit() {
        return this.hit;
    }
}
