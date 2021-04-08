package model.entities;

import model.utilities.BrickStatus;

/**
 * A generic interface which represents an {@link GameObj} that can be destruct. 
 *
 */
public interface Brick {

    /**
     * setter for {@link Brick} status.
     * @param brickStatus
     */
    void setStatus(BrickStatus brickStatus);

    /**
     * getter for {@link Brick} status.
     * @return brick's status
     */
    BrickStatus getStatus();

    /**
     * getter for {@link Brick} durability.
     * @return brick's durability.
     */
    int getDurability();

    /**
     * decreases {@link Brick} durability by an integer value {@link damage}.
     * @param ballDamage
     */
    void decreaseDurability(int ballDamage);

}
