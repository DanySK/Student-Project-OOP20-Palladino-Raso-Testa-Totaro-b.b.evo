
package model.entities;
import java.util.HashMap;
import java.util.Map;

import com.sun.prism.Texture;

import controller.input.ComponentInput;
import controller.input.ComponentInputEmpty;
import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.GameObjStatus;
import model.physics.ComponentPhysics;
import model.physics.PwUpComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;
import view.graphics.PwUpComponentGraphics;

public class Brick extends GameObjectImpl {

    private int durability;
    private final Map<GameObject, Boundaries> hit = new HashMap<>();
    private final String texturePath;

    protected Brick(final Position pos, final double speed, final int height, final int width, final int durability, final GameObjStatus status, final String texturePath) {
        super(pos, null, speed, height, width, null, null, null, status);
        this.durability = durability;
        this.texturePath = texturePath;
    }

    /**
     * drops the {@link PowerUp} if the {@link GameObjStatus} is set on DROP_POWERUP and its durability is <= 0.
     * @return a new PowerUp
     */
    public PowerUp dropPowerUp() {
        return new PowerUp(this.getPos(), new DirVector(0, -1), this.getSpeed(),
                this.getHeight(), this.getWidth(), new PwUpComponentPhysics(),
                new ComponentInputEmpty(), new PwUpComponentGraphics(this.texturePath), this.getStatus(), this.texturePath);
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
        private String texturePath;

        /**
         * used to build the brick.
         * @return brick builder
         */
        public Brick build() {
            return new Brick(this.pos, this.speed, this.height, this.width, this.durability, this.status, this.texturePath);
        }

        public Builder setTexture(final String texturePath) {
            this.texturePath = texturePath;
            return this;
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
    public void updatePhysics(final double timeElapsed, final GameBoardImpl world) {
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
