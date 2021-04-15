
package model.utilities;

import java.util.Arrays;
import java.util.List;

/**
 * An enum which represents the {@link BrickStatus} of the various gamObj in this game.
 */
public enum BrickStatus {
    /**
     * Represents a gamObj that can not be destroyed.
     */
    NOT_DESTRUCTIBLE,

    /**
     * Represents a gamObj that can be destroyed.
     */
    DESTRUCTIBLE,

    /**
     * Represents a gamObj that can drop a PowerUp.
     */
    DROP_POWERUP,

    /**
     * Represent an empty space/entity.
     */
    EMPTY;

    /**
     * Used to know all the possible status of gamObj.
     * 
     * @return the list of this {@link Angle}
     */
    public static List<BrickStatus> getStatusList() {
        return Arrays.asList(BrickStatus.values());
    }
}
