package model.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PowerUpType {
    /**
     * Used to represent a {@link PowerUpType} that speeds the ball up.
     */
    SPEED_UP(GameUtilities.POWERUP_ACTIVE_TIME, GameUtilities.SPEED_MODIFIER, 0, 0), 
    /**
     * Used to represent a {@link PowerUpType} that adds a life to the player's score.
     */
    LIFE_UP(0, 0, GameUtilities.DEFAULT_LIFE_MODIFIER, 0),
    /**
     * Used to represent a {@link PowerUpType} that increases the damage dealt by the ball.
     */
    DAMAGE_UP(GameUtilities.POWERUP_ACTIVE_TIME, 0,  0, GameUtilities.DAMAGE_MODIFIER),

    /**
     * Used to represent a {@link PowerUpType} that speeds the ball down.
     */
    SPEED_DOWN(GameUtilities.POWERUP_ACTIVE_TIME, -GameUtilities.SPEED_MODIFIER, 0, 0), 
    /**
     * Used to represent a {@link PowerUpType} that reduces a life to the player's score.
     */
    LIFE_DOWN(0, 0, -GameUtilities.DEFAULT_LIFE_MODIFIER, 0),
    /**
     * Used to represent a {@link PowerUpType} that decreases the damage dealt by the ball.
     */
    DAMAGE_DOWN(GameUtilities.POWERUP_ACTIVE_TIME, 0, 0, -GameUtilities.DAMAGE_MODIFIER); 

    private float activeTime;
    private float speedModifier;
    private int lifeModifier;
    private int damageModifier;
    private static final List<PowerUpType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Constructor for {@link PowerUpType.SPEED_UP} and {@link PowerUpType.SPEED_DOWN} types.
     * @param speedModifier
     * @param activeTime
     */
    PowerUpType(final float activeTime, final float speedModifier, final int lifeModifier, final int damageModifier) {
        this.speedModifier = speedModifier;
        this.activeTime = activeTime;
        this.lifeModifier = lifeModifier;
        this.damageModifier = damageModifier;
    }

    /**
     * Used to generate a random value between all the {@link PowerUpType} available.
     * @return {@link PowerUpType}
     */
    public static PowerUpType randomPowerUpType()  {
          return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public float getActiveTime() {
        return activeTime;
    }

    public float getSpeedModifier() {
        return speedModifier;
    }

    public int getLifeModifier() {
        return lifeModifier;
    }

    public int getDamageModifier() {
        return damageModifier;
    }
}