package model.entities;

import controller.input.ComponentInput;
import controller.input.ControllerInput;
import controller.physics.ComponentPhysics;
import model.utilities.Position;
import model.utilities.Status;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;

public class BrickImpl extends GameObjectImpl {
    
    public int durability;
    public Status status;
    
    public BrickImpl(Position pos, double speed, int height, int width,int durability) {
        super(pos, new DirVector(0,0), 0, height, width,null,null,null);
        this.durability = durability;
    }



    /**
     * {@inheritDoc}
     */
    public void setStatus(final Status status) {
            this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    public void decreaseDurability(final int ballDamage) {
        this.durability -= ballDamage;
    }

    /**
     * {@inheritDoc}
     */
    public int getDurability() {
        return this.durability;
    }

    @Override
    public void updatePhysics(int timeElapsed, GameBoardImpl world) {        
    }

    @Override
    public void updateInput(ControllerInput controller) {        
    }

    @Override
    public void updateGraphics(AdapterGraphics graphicsAdapter) {        
    }

}
