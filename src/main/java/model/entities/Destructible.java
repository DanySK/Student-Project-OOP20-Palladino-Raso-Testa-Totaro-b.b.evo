package model.entities;

/**
 * A generic interface which represents an {@link GameObj} that can be destruct. 
 *
 */
public interface Destructible extends GameObj {

    /**
     * Used to know the entity {@link GameObj} is destruct.
     * 
     * @return true if the entity {@link GameObj} has no more durability, false in other
     *         cases
     */
    Boolean isBroken();
    /**
     * Getter for durability of the entity.
     * @return the durability remains of the entity
     */
    int getDurability();
    /**
     * Used when a destructible entity is damaged to decrease his durability.
     */
    void decDurability();
}
