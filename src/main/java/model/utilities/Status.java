package model.utilities;

import java.util.Arrays;
import java.util.List;

/**
 * An enum which represents the {@link Status} of the various gamObj in this game.
 */
public enum Status {
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
     * @return the list of this {@link Direction}
     */
    public static List<Status> getStatusList() {
        return Arrays.asList(Status.values());
    }
}
