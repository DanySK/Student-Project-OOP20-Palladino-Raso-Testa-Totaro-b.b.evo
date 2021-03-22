package model.utilities;

import java.util.Arrays;
import java.util.List;

/**
 * An enum which represents the {@link Status} of the various gamObj in this game.
 */
public enum Status {
    /**
     * Represent a movable gamObj that can not destroy.
     */
    NOT_DESTR,

    /**
     * Represent a movable entity that can destroy.
     */
    DESTR,

    /**
     * Represent the last moments of hit gamObj to hit gamObj.
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
