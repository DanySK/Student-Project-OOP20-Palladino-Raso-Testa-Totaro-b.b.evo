package model.entities;

import model.utilities.Status;

/**
 * A generic interface which represents an {@link GameObj} that can be destruct. 
 *
 */
public interface Brick {

    /**
     * setter for {@link Brick} status.
     */
    void setStatus(Status status);

    /**
     * getter for {@link Brick} status.
     * @return brick's status
     */
    Status getStatus();

    /**
     * getter for {@link Brick} durability.
     * @return brick's durability.
     */
    int getDurability();

    /**
     * decreases {@link Brick} durability by an integer value {@link damage}.
     * @param damage
     */
    void decreaseDurability(int damage);

}
