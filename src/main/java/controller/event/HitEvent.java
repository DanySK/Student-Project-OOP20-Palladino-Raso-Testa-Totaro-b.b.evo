package controller.event;

import model.entities.GameObject;
import model.entities.GameObjectImpl;
import model.utilities.Boundaries;

public class HitEvent implements Event {

    private final GameObjectImpl gameObj;
    private final Boundaries bounds;

    public HitEvent(final GameObjectImpl gameObj, final Boundaries bounds) {
        this.gameObj = gameObj;
        this.bounds = bounds;
    }

    /**
     * 
     * @return gameObject
     */
    public GameObject getGameObj() {
        return gameObj;
    }

    /**
     * 
     * @return boundaries
     */
    public Boundaries getBounds() {
        return bounds;
    }

}