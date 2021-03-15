package controller.entities;

import model.entities.GameObject;
import model.utilities.GameObjectType;
import model.utilities.Position;

public class WallController extends GameObject{

    public WallController(int width, int height, Position position, GameObjectType type) {
        super(width, height, position, type);
    }
    

}
