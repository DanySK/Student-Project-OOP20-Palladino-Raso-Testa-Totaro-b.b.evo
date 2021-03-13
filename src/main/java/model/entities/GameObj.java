package model.entities;

import javafx.scene.shape.Rectangle;
import model.utilities.GameObjectType;
import model.utilities.Position;


/**
 * An interface for {@link GameObj} model.
 *
 */
public interface GameObj {

    /**
     * Used to know the space occupied by a GameObj.
     * 
     * @return the Bound that represents the occupied space.
     */
    Rectangle getBound();
    /**
     * Used to know the position of a GameObj in the game world.
     * 
     * @return the Position that keeps the (x,y) coordinates.
     */
    Position getPosition();
    /**
     * Used to know what kind of GameObj is.
     * 
     * @return the type of the GameObj.
     */
    GameObjectType getType();

}
