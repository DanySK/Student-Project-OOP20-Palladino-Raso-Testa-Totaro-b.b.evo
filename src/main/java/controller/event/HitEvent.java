package controller.event;

import model.entities.GameObjectImpl;
import model.utilities.Boundaries;
import model.utilities.GameObjectType;

public class HitEvent implements Event {
    private final GameObjectImpl gameObj;
    private final Boundaries bounds;

    public HitEvent(final GameObjectImpl gameObj, final Boundaries bounds) {
        this.gameObj = gameObj;
        this.bounds = bounds;
    }

    /**
     * 
     * @return which side has been hit
     */
    public Boundaries getBoundaries() {
        return this.bounds;
    }

}
