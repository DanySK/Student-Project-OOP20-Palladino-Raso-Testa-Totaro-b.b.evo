package model.entities;

import controller.input.ControllerInput;
import controller.physics.BallComponentPhysics;
import model.utilities.Position;
import model.utilities.Velocity;
import view.graphics.AdapterGraphics;
import view.graphics.BallComponentGraphics;

public final class Ball extends GameObjectImpl {

    private Ball(final Position pos, final Velocity vel, final double speed, final int height, final int width) {
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
     * simulate a vertical collision.
     */
    public void flipVelOnY() {
        this.setVel(new Velocity(this.getVel().getX(), -this.getVel().getY()));
    }

    /**
     * 
     * simulate a horizontal collision.
     */
    public void flipVelOnX() {
        this.setVel(new Velocity(-this.getVel().getX(), this.getVel().getY()));
    }

    public static class Builder {

    }

}
