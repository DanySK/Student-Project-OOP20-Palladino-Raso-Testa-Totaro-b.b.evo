package model.entities;

import java.awt.Rectangle;

import controller.utilities.MapGame;
import controller.utilities.MapIO;
import model.utilities.Direction;
import model.utilities.GameObjectType;
import model.utilities.Position;
import model.utilities.Status;

public class MovableGameObj extends GameObject implements Movable {
    private Status status;
    private Direction direction;
    private final int speed;
    private static final Position MIDDLELEFT = new Position(0, ((MapGame.getRows() - 1) / 2) * MapGame.getScale());
    private static final Position MIDDLERIGHT  = new Position(MapGame.getColumns() * MapGame.getScale(), 
                                                             ((MapGame.getRows() - 1) / 2) * MapGame.getScale());
    /**
     * Construct an abstract movable.
     * 
     * @param width     {@link GameObject}
     * @param height    {@link GameObject}
     * @param position  {@link GameObject}
     * @param type      {@link GameObject}
     * @param direction the first direction of the gameObj
     * @param status    the initial status of the gameObj
     * @param speed     the first speed of the gameObj
     */
    protected MovableGameObj(final int width, final int height, final Position position, final GameObjectType type,
            final Direction direction, final Status status, final int speed) {
        super(width, height, position, type);
        this.status = status;
        this.direction = direction;
        this.speed = speed;
    }
    /**
     * {@inheritDoc}
     */
     @Override
    public synchronized Direction getDirection() {
        return this.direction;
    }
    /**
     * {@inheritDoc}
     */
     @Override
    public synchronized void setDirection(final Direction newDir) {
        this.direction = newDir;
    }
    /**
     * {@inheritDoc}
     */
     @Override
    public synchronized Status getStatus() {
        return this.status;
    }
    /**
     * {@inheritDoc}
     */
     @Override
    public synchronized void setStatus(final Status newStatus) {
        this.status = newStatus;
    }
    /**
     * {@inheritDoc}
     */
     @Override
    public Rectangle getBoundsAt(final Position position) {
        return new Rectangle(position.getX(), position.getY(), this.getWidth(), this.getHeight());
    }
    /**
     * {@inheritDoc}
     */
     @Override
    public Position nextPosition() {
        if (this.getPosition().getX() <= MIDDLELEFT.getX()) {
            return new Position(MIDDLERIGHT.getX() - 10, MIDDLERIGHT.getY());
        }
        if (this.getPosition().getX() >= MIDDLERIGHT.getX()) {
            return new Position(MIDDLELEFT.getX() + 10, MIDDLELEFT.getY());
        }
        switch (this.getDirection()) {
        case DOWN:
            return new Position(this.getPosition().getX(), this.getPosition().getY() + speed);
        case LEFT:
            return new Position(this.getPosition().getX() - speed, this.getPosition().getY());
        case RIGHT:
            return new Position(this.getPosition().getX() + speed, this.getPosition().getY());
        case UP:
            return new Position(this.getPosition().getX(), this.getPosition().getY() - speed);
        default:
            return new Position(this.getPosition().getX(), this.getPosition().getY());
        }
    }
}
