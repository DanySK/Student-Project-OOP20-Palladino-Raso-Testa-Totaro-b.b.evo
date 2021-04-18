
package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ComponentInputEmpty;
import controller.input.ControllerInput;
import model.utilities.Position;
import resource.routing.PowerUpDropTexture;
import model.utilities.BrickStatus;
import model.physics.ComponentPhysicsEmpty;
import model.physics.PwUpComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;
import view.graphics.BrickComponentGraphics;
import view.graphics.PwUpComponentGraphics;

public class Brick extends GameObjectImpl {

    private static final long serialVersionUID = 6692878597912001932L;
    private int durability;
    private final Map<GameObject, Boundaries> hit = new HashMap<>();
    private final String texturePath;
    private BrickStatus status;

    protected Brick(final Position pos, final int height, final int width, final int durability, final BrickStatus status, final String texturePath) {
        super(pos, new DirVector(0, 0), 0, height, width, new ComponentPhysicsEmpty(), new ComponentInputEmpty(), new BrickComponentGraphics(texturePath));
        this.durability = durability;
        this.texturePath = texturePath;
        this.status = status;
    }

    /**
     * drops the {@link PowerUp} if the {@link BrickStatus} is set on DROP_POWERUP and its durability is <= 0.
     * @return a new PowerUp
     */
    public PowerUp dropPowerUp() {
        //controllo dal brick per prendere il tema
        //PowerUpDropTexture. metodo col nome del tema
        return new PowerUp(this.getPos(), this.getHeight(), this.getWidth(), PowerUpDropTexture.DROP_TEXTURE_GALAGA.getPath());
    }


    /**
     * Builder class used to build the brick.
     *
     */
    public static final class Builder {
        private Position pos;
        private int height;
        private int width;
        private int durability;
        private BrickStatus status;
        private String texturePath;

        /**
         * used to build the brick.
         * @return brick builder
         */
        public Brick build() {
            if (this.durability <= 0 || this.height <= 0 || this.width <= 0 || this.pos == null || this.texturePath == null ) {
                throw new IllegalStateException();
            }
            return new Brick(this.pos, this.height, this.width, this.durability, this.status, this.texturePath);
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
        public Builder setStatus(final BrickStatus status) {
            this.status = status;
            return this;
        }
    }

    /**
     * getter for gameoobject's status.
     * @return gameoobject's status
     */
    public BrickStatus getStatus() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        super.getComponentPhysics().update(timeElapsed, this, world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final ControllerInput controller) {
        super.getComponentInput().update(this, controller);
        }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) { 
        this.getComponentGraphics().update(this, graphicsAdapter);
    }

    /**
     * getter for hit map.
     * @return map
     */
    public Map<GameObject, Boundaries> getHit() {
        return this.hit;
    }
 
}
