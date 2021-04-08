package controller.event;

import model.entities.GameObjectImpl;
import model.utilities.Boundaries;
import model.utilities.GameObjectType;

public class HitEvent implements Event {
    private final GameObjectImpl gameObj;
    private final Boundaries bounds;
    
    public HitEvent(GameObjectImpl gameObj, Boundaries bounds) {
        this.gameObj = gameObj;
        this.bounds = bounds;
    }

}
