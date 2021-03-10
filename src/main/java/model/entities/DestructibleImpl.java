package model.entities;

import javafx.geometry.Bounds;
import model.utilities.GameObjectType;
import model.utilities.Position;

public class DestructibleImpl extends GameObjectImpl implements Destructible{

    private int durability;
    private Bounds bound;    

    public DestructibleImpl(int width, int height, Position position, GameObjectType type, int durability) {
        super(width, height, position, type);
        this.durability = durability;
    }

    @Override
    public Bounds getBound() {
        return bound;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public GameObjectType getType() {
        return this.type;
    }

    @Override
    public Boolean isBroken() {
        if(this.durability <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public void decDurability() {
        this.durability--;
        
    }

}
