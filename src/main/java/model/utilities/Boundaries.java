
package model.utilities;

import java.util.Arrays;
import java.util.List;

public enum Boundaries {
    /**
     * Represents the upper bound.
     */
    UPPER,
    /**
     * Represents the lower bound.
     */
    LOWER,
    /**
     * Represents the left bound.
     */
    SIDE_LEFT,
    /**
     * Represents the right bound.
     */
    SIDE_RIGHT;

    /**
     * Used to know all the possible boundaries sides of a gameObject.
     * 
     * @return the list of this {@link Boundaries}
     */
    public static List<Boundaries> getBoundariesList() {
        return Arrays.asList(Boundaries.values());
    }
}
