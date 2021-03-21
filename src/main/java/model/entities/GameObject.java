package model.entities;

import javafx.scene.shape.Rectangle;
import model.utilities.GameObjectType;
import model.utilities.Position;

/**
 * A class for {@link GameObject} model.
 */
public abstract class GameObject {
    /**
     * 
     */
    private final int width;
    private final int height;
    private Position position;
    private final GameObjectType type;

    /**
     * Construct an implementation of {@link GameObject}.
     * 
     * @param width    the width of the gameObj.
     * @param height   the height of the gameObj.
     * @param position the position of the gameObj.
     * @param type     the type of the gameObj.
     */
    protected GameObject(final int width, final int height, final Position position, final GameObjectType type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
    }

    /**
     * Used to know the space occupied by an GameObject.
     * 
     * @return the Rectangle that represents the occupied space.
     */
    public Rectangle getBound() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }

    /**
     * Used to know the position of an GameObject in the game world.
     * 
     * @return the Position that keeps the (x,y) coordinates.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Used to know what kind of GameObject is.
     * 
     * @return the type of the GameObject.
     */
    public GameObjectType getType() {
        return this.type;
    }

    /**
     * An implementation of the setter for the classes that needs to extend
     * SimpleGameObj.
     * 
     * @param newPosition the new position to set
     */
    public void setPosition(final Position newPosition) {
        this.position = newPosition;
    }

    /**
     * An implementation of the getter for the classes that needs to extend
     * SimpleGameObj.
     * 
     * @return the width of the gameObj
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * An implementation of the getter for the classes that needs to extend
     * SimpleGameObj.
     * 
     * @return the height of the gameObj
     */
    public int getHeight() {
        return this.height;
    }

}
