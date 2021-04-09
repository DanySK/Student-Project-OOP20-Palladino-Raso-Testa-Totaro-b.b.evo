package model.utilities;

import java.util.Arrays;
import java.util.List;

/**
 * An enum which represents the {@link GameObjStatus} of the various gamObj in this game.
 */
public enum GameObjStatus {
    /**
     * Represents a gamObj that can not be destroyed.
     */
    NOT_DESTR,

    /**
     * Represents a gamObj that can be destroyed.
     */
    DESTR,

    /**
     * Represents a gamObj that can drop a PowerUp.
     */
    DROP_POWERUP,

    /**
     * Represent a broken gameObject.
     */
    BROKEN;

    /**
     * Used to know all the possible status of gamObj.
     * 
     * @return the list of this {@link Angle}
     */
    public static List<GameObjStatus> getStatusList() {
        return Arrays.asList(GameObjStatus.values());
    }
}
