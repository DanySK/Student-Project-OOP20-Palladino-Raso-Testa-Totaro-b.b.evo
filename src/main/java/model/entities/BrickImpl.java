package model.entities;

import controller.input.ControllerInput;
import controller.physics.ComponentPhysics;
import model.utilities.Position;
import model.utilities.Status;
import model.utilities.Velocity;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;

public class BrickImpl extends GameObjectImpl implements Brick {
    public int durability;
    public Status status;

    public BrickImpl(Position pos, Velocity vel, double speed, int height, int width, ComponentPhysics physics,
            ComponentGraphics graphics) {
        super(pos, vel, speed, height, width, physics, graphics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final Status status) {
            this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseDurability(final int ballDamage) {
        this.durability -= ballDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
