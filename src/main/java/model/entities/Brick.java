
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

    private int durability;
    private final Map<GameObject, Boundaries> hit = new HashMap<>();

    public Brick(final Position pos, final double speed, final int height, final int width, final int durability, final GameObjStatus status) {
        super(pos, new DirVector(0, 0), speed, height, width, null, null, null, status);
        this.durability = durability;
    }

    /**
     * Builder class used to build the brick.
     *
     */
    public static final class Builder {
        private Position pos;
        private double speed;
        private int height;
        private int width;
        private int durability;
        private GameObjStatus status;

        /**
         * used to build the brick.
         * @return brick builder
         */
        public Brick build() {
            return new Brick(this.pos, this.speed, this.height, this.width, this.durability, this.status);
        }
        /**
         * setter for the position. 
         * @param pos
         * @return brick builder
         */
        public Builder setPos(final Position pos) {
            this.pos = pos;
            return this;
        }

        /**
         * setter for the speed.
         * @param speed
         * @return brick builder
         */
        public Builder setSpeed(final double speed) {
            this.speed = speed;
            return this;
        }
        /**
         * setter for the height. 
         * @param height
         * @return brick builder
         */
        public Builder setHeight(final int height) {
            this.height = height;
            return this;
        }
        /**
         * setter for the width.
         * @param width
         * @return brick builder
         */
        public Builder setWidth(final int width) {
            this.width = width;
            return this;
        }
        
        public Builder setDurability(final int durability) {
            this.durability = durability;
            return this;
        }
        /**
         * setter for the status .
         * @param status
         * @return brick builder
         */
        public Builder setStatus(final GameObjStatus status) {
            this.status = status;
            return this;
        }
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
 
}
