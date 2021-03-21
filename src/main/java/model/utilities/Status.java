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
    TO_HIT,
    /**
     * Represent a movable entity that can destroy.
     */
    HIT,
    /**
     * Represent the last moments of hit gamObj to hit gamObj.
     */
    HIT_END;
    /**
     * Used to know all the possible status of gamObj.
     * 
     * @return the list of this {@link Direction}
     */
    public static List<Status> getStatusList() {
        return Arrays.asList(Status.values());
    }
}
