package model.entities;

import java.awt.Rectangle;

import model.utilities.Direction;
import model.utilities.Position;
import model.utilities.Status;

public interface Movable {

    /**
     * Used to set a new position.
     * 
     * @param newPos {@link Position}.
     */
    void setPosition(Position newPos);

    /**
     * Used to get the direction of the gameObject.
     * 
     * @return the actual Direction {@link Direction}.
     */
    Direction getDirection();

    /**
     * Used to set the direction of the gameObject.
     * 
     * @param newDir the new direction to set.
     */
    void setDirection(Direction newDir);

    /**
     * Used to get the status of the gameObject.
     * 
     * @return the actual status of gameObject.
     */
    Status getStatus();

    /**
     * Used to set the status of the gameObject.
     * 
     * @param newStatus the status to set for the gameObject.
     */
    void setStatus(Status newStatus);

    /**
     * Used to get the bounds at an input position.
     * 
     * @param position the position to calculate rectangle from.
     * @return the rectangle {@link Rectangle} bounds for the gameObject at position
     *         {@link Position}.
     */
    Rectangle getBoundsAt(Position position);

    /**
     * Used to calculate the next position of the gameObject.
     * 
     * @return next position for the gameObject.
     */
    Position nextPosition();

}
