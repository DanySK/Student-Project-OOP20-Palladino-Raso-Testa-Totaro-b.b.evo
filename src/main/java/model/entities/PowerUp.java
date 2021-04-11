
package model.entities;

import model.utilities.GameObjStatus;
import model.utilities.Position;
import model.utilities.PowerUpType;

public class PowerUp extends Brick {
    private PowerUpType pwtype;

    private float activeTime;
    private float speedModifier;
    private int lifeModifier;
    private int damageModifier;

    public PowerUp(final Position pos, final double speed, final int height, final int width, final int durability, final GameObjStatus gameObjStatus) {
        super(pos, speed, height, width, durability, gameObjStatus);
        this.pwtype = PowerUpType.randomPowerUpType();
        this.setActiveTime(PowerUpType.valueOf(this.pwtype.toString()).getActiveTime());
        this.setDamageModifier(PowerUpType.valueOf(this.pwtype.toString()).getDamageModifier());
        this.setLifeModifier(PowerUpType.valueOf(this.pwtype.toString()).getLifeModifier());
        this.setSpeedModifier(PowerUpType.valueOf(this.pwtype.toString()).getSpeedModifier());
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
     * 
     * @param ms amount of milliseconds to wait
     */
    public void waitSeconds(final float ms) {
        try {
            Thread.sleep((long) ms);
        }
        catch  (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void damagePowerUp(int damageModifier) {
        
    }
    
    public void lifePowerUp(int lifeModifier) {
        
    }
    
    public void speedPowerUp(float speedModifier) {
        
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
     * setter for the damage modifier.
     * @param damageModifier
     */
    public void setDamageModifier(final int damageModifier) {
        this.damageModifier = damageModifier;
    }

    /**
     * getter for the life modifier. 
     * @return the life modifier
     */
    public int getLifeModifier() {
        return lifeModifier;
    }

    /**
     * setter for the life modifier.
     * @param lifeModifier
     */
    public void setLifeModifier(final int lifeModifier) {
        this.lifeModifier = lifeModifier;
    }

    /**
     * getter for the speed modifier.
     * @return the speed modifier
     */
    public float getSpeedModifier() {
        return speedModifier;
    }

    /**
     * setter for the speed modifier.
     * @param speedModifier
     */
    public void setSpeedModifier(final float speedModifier) {
        this.speedModifier = speedModifier;
    }

    /**
     * getter for the active time.
     * @return active time in seconds
     */
    public float getActiveTime() {
        return activeTime;
    }

    /**
     * setter for the active time.
     * @param activeTime
     */
    public void setActiveTime(final float activeTime) {
        this.activeTime = activeTime;
    }
}
