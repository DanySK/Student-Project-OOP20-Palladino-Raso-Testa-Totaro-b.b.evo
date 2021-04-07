package model.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PowerUpType {
    /**
     * Used to represent a {@link PowerUpType} that speeds the ball up.
     */
    SPEEDUP(1, 5), 
    /**
     * Used to represent a {@link PowerUpType} that adds a life to the player's score.
     */
    LIFE_UP(1),
    /**
     * Used to represent a {@link PowerUpType} that increases the damage dealt by the ball.
     */
    DAMAGE_UP(5, (int) 1),

    /**
     * Used to represent a {@link PowerUpType} that speeds the ball down.
     */
    SPEED_DOWN(-1, 5), 
    /**
     * Used to represent a {@link PowerUpType} that reduces a life to the player's score.
     */
    LIFE_DOWN(-1),
    /**
     * Used to represent a {@link PowerUpType} that decreases the damage dealt by the ball.
     */
    DAMAGE_DOWN(5, (int) -1); 

    private float speedModifier;
    private float activeTime;
    private int lifeModifier;
    private static final List<PowerUpType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Constructor for {@link PowerUpType.SPEED_UP} and {@link PowerUpType.SPEED_DOWN} types.
     * @param speedModifier
     * @param activeTime
     */
    PowerUpType(final float speedModifier, final float activeTime) {
        this.speedModifier = speedModifier;
        this.activeTime = activeTime;
    }

    /**
     * Constructor for {@link PowerUpType.DAMAGE_UP} and {@link PowerUpType.DAMAGE_DOWN} types.
     * @param lifeModifier
     */
    PowerUpType(final int lifeModifier) {
        this.lifeModifier = lifeModifier;
    }

    /**
     * Constructor for {@link PowerUpType.LIFE_UP} and {@link PowerUpType.LIFE_DOWN} types.
     * @param activeTime
     * @param lifeModifier
     */
    PowerUpType(final float activeTime, final int lifeModifier) {
        this.activeTime = activeTime;
        this.lifeModifier = lifeModifier;
    }

    /**
     * Used to generate a random value between all the {@link PowerUpType} available.
     * @return {@link PowerUpType}
     */
    public static PowerUpType randomPowerUpType()  {
          return VALUES.get(RANDOM.nextInt(SIZE));
    }
}