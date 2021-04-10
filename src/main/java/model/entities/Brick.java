
package model.entities;
import java.util.HashMap;
import java.util.Map;

import controller.input.ComponentInput;
import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.GameObjStatus;
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


    private final Map<GameObject, Boundaries> hit = new HashMap<>();

    public Brick(final Position pos, final double speed, final int height, final int width, final int durability, final GameObjStatus status) {
        super(pos, new DirVector(0, 0), 0, height, width, null, null, null, status);
        this.durability = durability;
    }

    /**
     * 
     */
    @Override
    public GameObjStatus getStatus() {
        return this.getStatus();
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

    /**
     * 
     */
    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        super.getComponentPhysics().update(timeElapsed, this, world);
    }

    /**
     * 
     */
    @Override
    public void updateInput(final ControllerInput controller) {
        super.getComponentInput().update(this, controller);
        }

    /**
     * 
     */
    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) { 
        this.getComponentGraphics().update(this, graphicsAdapter);
    }

    /**
     * 
     * @return map
     */
    public Map<GameObject, Boundaries> getHit() {
        return this.hit;
    }
    
    //creare builder
}
