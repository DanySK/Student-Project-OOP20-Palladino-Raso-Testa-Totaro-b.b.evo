package controller.entities;

import java.util.Optional;

import controller.physics.ComponentPhysics;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import model.entities.BrickImpl;
import model.entities.GameObject;
import model.utilities.GameObjectType;
import model.utilities.Position;
import model.utilities.Velocity;
import view.graphics.ComponentGraphics;

public class BrickControllerImpl extends BrickImpl implements BrickController{

    public BrickControllerImpl(Position pos, Velocity vel, double speed, int height, int width,
            ComponentPhysics physics, ComponentGraphics graphics) {
        super(pos, vel, speed, height, width, physics, graphics);
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
