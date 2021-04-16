package controller.event;

import java.util.Optional;

import model.entities.GameObject;
import model.utilities.Boundaries;

public class HitEvent implements Event {

    private final Optional<GameObject> gameObj;
    private final Optional<Boundaries> bounds;

    public HitEvent(final Optional<GameObject> gameObj, final Optional<Boundaries> bounds) {
        this.gameObj = gameObj;
        this.bounds = bounds;
    }

    /**
     * 
     * @return the gameObject in question
     */
    public Optional<GameObject> getGameObj() {
        return this.gameObj;
    }

    /**
     * 
     * @return the wall on which the collision occurred returns
     */
    public Optional<Boundaries> getBounds() {
        return this.bounds;
    }

}
