package model.entities;

import javafx.scene.shape.Rectangle;
import model.utilities.GameObjectType;
import model.utilities.Position;

/**
 * 
 *
 */
public class GameObjectImpl implements GameObj {

    public final int width;
    public final int height;
    public Position position;
    public final GameObjectType type;

    public GameObjectImpl(final int width, final int height, final Position position, final GameObjectType type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public GameObjectType getType() {
        return this.type;
    }

}
