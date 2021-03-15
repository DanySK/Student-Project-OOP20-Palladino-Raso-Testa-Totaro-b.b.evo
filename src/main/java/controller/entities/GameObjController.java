package controller.entities;

import java.util.Optional;

import javafx.scene.shape.Rectangle;
import model.utilities.GameObjectType;

public interface GameObjController extends Renderable {
    /**
     * Used to update the settings of an {@link Entity}.
     */
    void updateView();
    /**
     * Used to find a collision.
     * 
     * @param collider the area to test the collision.
     * @return the {@link Entity} that collided (if there's a collision).
     */
    //Optional<GameObj> getCollision(Rectangle collider);
    /**
     * Used to remove logically the {@link Entity} from the game.
     */
    void remove();
    /**
     * Used to know if the {@link Entity} has been removed logically from the game.
     * 
     * @return true if the {@link Entity} has been removed, false otherwise
     */
    boolean isRemoved();
    /**
     * Used to know the type of the entity.
     * @return the type
     */
    GameObjectType getType();
}
