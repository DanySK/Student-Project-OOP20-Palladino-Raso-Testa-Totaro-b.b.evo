package controller.entities;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import model.entities.Brick;
import model.entities.GameObject;
import model.physics.ComponentPhysics;
import model.utilities.GameObjectType;
import model.utilities.Position;
import model.utilities.DirVector;
import view.graphics.ComponentGraphics;

public class BrickControllerImpl extends Brick implements BrickController{

    public BrickControllerImpl(Position pos, double speed, int height, int width, int durability) {
        super(pos, speed, height, width, durability);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void updateView() {        
    }

    @Override
    public Optional<GameObject> getCollision(Rectangle collider) {
        return null;
    }

    @Override
    public void remove() {        
    }

    @Override
    public boolean isRemoved() {
        return false;
    }

    @Override
    public GameObjectType getType() {
        return this.getType();
    }

    @Override
    public void render(Scene g) {        
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public void decreaseDurability() {        
    }
}
