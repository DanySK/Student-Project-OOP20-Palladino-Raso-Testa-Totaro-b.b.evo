package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ComponentInput;
import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.BrickStatus;
import model.physics.ComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;

public class BrickImpl extends GameObjectImpl {
    
    public int durability;
    public BrickStatus brickStatus;
    
    private final Map<Ball, Boundaries> hitBall = new HashMap<>();

    public BrickImpl(Position pos, double speed, int height, int width,int durability) {
        super(pos, new DirVector(0,0), 0, height, width,null,null,null);
        this.durability = durability;
    }



    /**
     * {@inheritDoc}
     */
    public void setStatus(final BrickStatus brickStatus) {
            this.brickStatus = brickStatus;
    }

    /**
     * {@inheritDoc}
     */
    public BrickStatus getStatus() {
        return this.brickStatus;
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

    public Map<Ball, Boundaries> getHitBall() {
        return this.hitBall;
    }
}
