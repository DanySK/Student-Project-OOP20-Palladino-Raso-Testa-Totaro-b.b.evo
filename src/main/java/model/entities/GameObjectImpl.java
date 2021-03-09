package model.entities;

import javafx.geometry.Bounds;
import model.utilities.GameObjectType;
import model.utilities.Position;

/**
 * 
 *
 */
public class GameObjectImpl implements GameObj {

    private final int width;
    private final int height;
    private Position position;
    private final GameObjectType type;

    public GameObjectImpl(final int width, final int height, final Position position, final GameObjectType type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
    }

    /**
     *
     */
    @Override
    public Bounds getBound() {
        return null;
    }

    /**
     *
     */
    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     */
    @Override
    public GameObjectType getType() {
        // TODO Auto-generated method stub
        return null;
    }

}
