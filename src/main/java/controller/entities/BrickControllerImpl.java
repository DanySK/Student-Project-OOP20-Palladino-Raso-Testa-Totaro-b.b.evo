package controller.entities;

import model.entities.BrickImpl;
import model.utilities.GameObjectType;
import model.utilities.Position;

public class BrickControllerImpl extends BrickImpl{

    public BrickControllerImpl(int width, int height, Position position, GameObjectType type, int durability) {
        super(width, height, position, type, durability);
    }


}
