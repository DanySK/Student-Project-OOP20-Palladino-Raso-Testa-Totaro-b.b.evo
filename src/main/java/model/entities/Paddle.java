package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ControllerInput;
import controller.input.PaddleComponentInput;
import model.physics.PaddleComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.GameObjStatus;
import model.utilities.GameObjectType;
import model.utilities.Position;
import view.graphics.AdapterGraphics;
import view.graphics.PaddleComponentGraphics;

public class Paddle extends GameObjectImpl {

    private static final int PADDLE_SPEED = 400;
    private final Map<Ball, Boundaries> hitBall = new HashMap<>();

    public Paddle(final Position pos, final int height, final int width) {
        super(pos, new DirVector(0, 0), PADDLE_SPEED, height, width, new PaddleComponentPhysics(), 
                new PaddleComponentInput(), new PaddleComponentGraphics(), GameObjStatus.NOT_DESTRUCTIBLE);
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
     * 
     * @return a map containing the info of the ball in the game to facilitate the bounce with the paddle
     */
    public Map<Ball, Boundaries> getHitBall() {
        return this.hitBall;
    }

    public static final class Builder {

        private int height;
        private int width;
        private Position pos;

        /**
         * 
         * @param width
         * @return himself
         */
        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        /**
         * 
         * @param height
         * @return himself
         */
        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        /**
         * 
         * @param pos
         * @return himself
         */
        public Builder position(final Position pos) {
            this.pos = pos;
            return this;
        }

        /**
         * builds the paddle if the characteristics are valid.
         * @return the new Paddle object
         */
        public Paddle build() {
            if (this.height <= 0 || this.width <= 0 || this.pos == null) {
                throw new IllegalStateException();
            }
            return new Paddle(this.pos, this.height, this.width);
        }
    }
}
