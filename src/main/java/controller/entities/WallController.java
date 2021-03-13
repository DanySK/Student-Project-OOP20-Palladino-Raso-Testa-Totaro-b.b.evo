package controller.entities;

import model.entities.GameObjectImpl;
import model.utilities.GameObjectType;
import model.utilities.Position;

public class WallController extends GameObjectImpl{

    public WallController(int width, int height, Position position, GameObjectType type) {
        super(width, height, position, type);
    }
    

}
