package model.entities;

import controller.input.ComponentInputEmpty;
import controller.input.ControllerInput;
import model.physics.ComponentPhysicsEmpty;
import model.utilities.DirVector;
import model.utilities.GameObjStatus;
import model.utilities.Position;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphicsEmpty;

public class GameObjectEmpty extends GameObjectImpl {

    /**
     * Create an empty space/object.
     * @param pos position
     * @param height dimension of the empty space
     * @param width dimension of the empty space
     */
    public GameObjectEmpty(final Position pos, final int height, final int width) {
        super(pos, new DirVector(0, 0), 0, height, width, new ComponentPhysicsEmpty(), new ComponentInputEmpty(), new ComponentGraphicsEmpty(), GameObjStatus.EMPTY);
    }

    /**
     * This method is only to maintain a reference in space, simple it's an empty object.
     */
    @Override
    public void updatePhysics(final double timeElapsed, final GameBoardImpl board) {
        this.getComponentPhysics().update(timeElapsed, this, board);
    }

    /**
     * This method is only to maintain a reference in space, simple it's an empty object.
     */
    @Override
    public void updateInput(final ControllerInput controller) {
        this.getComponentInput().update(this, controller);
    }

    /**
     * This method is only to maintain a reference in space, simple it's an empty object.
     */
    @Override
    public void updateGraphics(final AdapterGraphics adapterGraphics) {
        this.getComponentGraphics().update(this, adapterGraphics);
    }

}
