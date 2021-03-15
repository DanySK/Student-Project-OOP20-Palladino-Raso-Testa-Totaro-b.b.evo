package model.entities;

/**
 * A generic interface which represents an {@link GameObj} that can be destruct. 
 *
 */
public interface Brick {

    /*true if the brick has no more durability, false in other cases*/    
    Boolean isBroken();
    
    /*getter for the durability integer*/
    int getDurability();
    
    /*decreases bricks's durability by an integer number*/
    void decreaseDurability(int damage);

}
