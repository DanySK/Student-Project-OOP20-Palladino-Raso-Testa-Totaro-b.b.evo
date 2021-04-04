package view.entities;

import model.utilities.Direction;
import model.utilities.Status;

public interface MovableView extends EntityView {
    /**
     * Used to update the position of the entity in the graphic view.
     * 
     * @param status the new status of entity.
     */
    void updateStatus(Status status);
    /**
     * Used to update the direction of the entity in the graphic view.
     * 
     * @param newDir the new direction of entity.
     */
    void updateDirection(Direction newDir);
}
