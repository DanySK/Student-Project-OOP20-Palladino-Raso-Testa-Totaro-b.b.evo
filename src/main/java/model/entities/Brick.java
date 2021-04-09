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

public class Brick extends GameObjectImpl {

    /**
     * 
     */
    private int durability;
    /**
     * 
     */
    private BrickStatus brickStatus;

    private final Map<Ball, Boundaries> hitBall = new HashMap<>();

    public Brick(final Position pos, final double speed, final int height, final int width, final int durability, BrickStatus brickStatus) {
        super(pos, new DirVector(0, 0), 0, height, width, null, null, null);
        this.durability = durability;
        this.brickStatus = brickStatus;
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
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
    }

    @Override
    public void updateInput(final ControllerInput controller) {
    }

    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) { 
    }

    /**
     * 
     * @return map
     */
    public Map<Ball, Boundaries> getHitBall() {
        return this.hitBall;
    }
}
