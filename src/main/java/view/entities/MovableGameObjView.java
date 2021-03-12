package view.entities;

import java.awt.Graphics;
import java.util.Map;

/*
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
*/

import model.utilities.Direction;
import model.utilities.GameObjectType;
import model.utilities.Pair;
import model.utilities.Position;
import model.utilities.Status;

public class MovableGameObjView extends SimpleGameObjView implements MovableView {

    private Status status;
    private Direction direction;
    private final Map<Pair<Direction, Status>, BufferedImage> images;
    
    @Override
    public GameObjectType getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePosition(Position position) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateStatus(Status status) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDirection(Direction newDir) {
        // TODO Auto-generated method stub

    }

}
