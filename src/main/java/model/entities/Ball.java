package model.entities;

import controller.input.ControllerInput;
import controller.physics.BallComponentPhysics;
import model.utilities.DirVector;
import model.utilities.Position;
import view.graphics.AdapterGraphics;
import view.graphics.BallComponentGraphics;

public final class Ball extends GameObjectImpl {

    private Ball(final Position pos, final DirVector vel, final double speed, final int height, final int width) {
        super(pos, vel, speed, height, width, new BallComponentPhysics(), new BallComponentGraphics());
    }

    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        this.getComponentPhysics().update(timeElapsed, this, world);
    }

    @Override
    public void updateInput(final ControllerInput controller) {
        this.getComponentInput().update(this, controller);
    }

    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) {
        this.getComponentGraphics().update(this, graphicsAdapter);
    }

    /**
     * 
     * vertical collision.
     */
    public void flipVelOnY() {
        this.setVel(new DirVector(this.getVel().getX(), -this.getVel().getY()));
    }

    /**
     * 
     * horizontal collision.
     */
    public void flipVelOnX() {
        this.setVel(new DirVector(-this.getVel().getX(), this.getVel().getY()));
    }

    public static class Builder {

        private int height;
        private int width;
        private double speed;
        private Position pos;
        private DirVector dir;

        /**
         * @param dir
         * @return return himself
         */
        public Builder direction(final DirVector dir) {
            this.dir = dir;
            return this;
        }
        
        public Builder speed(final double speed) {
            this.speed = speed;
            return this;
        }
        
        public Builder height(final int height) {
            this.height = height;
            return this;
        }
        
        public Builder width(final int width) {
            this.width = width;
            return this;
        }
        
        public Builder position(final Position pos) {
            this.pos = pos;
            return this;
        }
    }

}
