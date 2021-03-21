package model.entities;

import model.utilities.GameObjectType;
import model.utilities.Position;

public class BrickImpl extends GameObject implements Brick {
    /**
     * 
     */
    public int durability;

    public BrickImpl(final int width, final int height, final Position position, final GameObjectType type, final int durability) {
        super(width, height, position, type);
            this.durability = durability;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isBroken() {
        if (this.durability <= 0) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseDurability(final int damage) {
        this.durability -= damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDurability() {
        return this.durability;
    }

}
