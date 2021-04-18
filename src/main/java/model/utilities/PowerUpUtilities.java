
package model.utilities;

/**
 * Contains information for {@link PowerUp} settings and default values.
 */
public final class PowerUpUtilities {

    /**
     * default damage dealt by the ball.
     */
    public static final int DEFAULT_BALL_DAMAGE = 1;

    /**
     * ball damage (modified  by powerups).
     */
    public static final int BALL_DAMAGE = 1;

    /**
     * damage modifier used by {@link PowerUp} to increase or decrease the damage dealt by the ball.
     */
    public static final int DAMAGE_MODIFIER = 1;

    /**
     * default active time for each {@link PowerUp} activation (seconds).
     */
    public static final long POWERUP_ACTIVE_TIME = 5;

    /**
     * default life moodifier used by {@link PowerUp} to increase or decrease player's lives.
     */
    public static final int DEFAULT_LIFE_MODIFIER = 1;

    /**
     * speed modifier used by {@link PowerUp} to speed the ball up or down.
     */
    public static final double SPEED_MODIFIER = 0.1;

    /**
     * speed for the {@link PowerUp} drop.
     */
    public static final double POWERUP_DROP_SPEED = 0.2;

    /**
     * default string for the inactive {@link PowerUp} .
     */
    public static final String DEFAULT_PWUP_STRING = "--";


    /**
     * default drop direction for {@link PowerUp}.
     */
    public static final DirVector POWERUP_DROP_DIR = new DirVector(0, 1);

    private PowerUpUtilities() {

    }

}
