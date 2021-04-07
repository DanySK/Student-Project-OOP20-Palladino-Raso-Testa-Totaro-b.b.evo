package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ControllerInput;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.Position;
import view.graphics.AdapterGraphics;

public class Paddle extends GameObjectImpl {

    private static final int PADDLE_SPEED = 400;
    private final Map<Ball, Boundaries> hitBall = new HashMap<>();

    public Paddle(final Position pos, final int height, final int width) {
        super(pos, new DirVector(0, 0), PADDLE_SPEED, height, width, new PaddleComponentPhysics(), PlayerComponentInput(), new PaddleComponentGraphics());
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
        
    }
}
