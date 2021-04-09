package controller.event;

import java.util.Optional;

import model.entities.GameObject;
import model.entities.GameObjectImpl;
import model.utilities.Boundaries;

public class HitEvent implements Event {

    private final Optional<GameObject> gameObj;
    private final Optional<Boundaries> bounds;

    public HitEvent(final GameObjectImpl gameObj, final Boundaries bounds) {
        this.gameObj = Optional.of(gameObj);
        this.bounds = Optional.of(bounds);
    }

    /**
     * 
     * @return gameObject
     */
    public Optional<GameObject> getGameObj() {
        return this.gameObj;
    }

    /**
     * 
     * @return boundaries
     */
    public Optional<Boundaries> getBounds() {
        return this.bounds;
    }

}
