package controller.input;

import model.entities.GameObject;
import model.utilities.DirVector;

public class PaddleComponentInput implements ComponentInput {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject obj, final ControllerInput controlInput) {
        if (controlInput.canMoveLef()) {
            obj.setVel(new DirVector(-1, 0));
        } else if (controlInput.canMoveRight()) {
            obj.setVel(new DirVector(1, 0));
        } else {
            obj.setVel(new DirVector(0, 0));
        }
    }

}
