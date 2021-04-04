package model.entities;

import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.Velocity;
import view.graphics.AdapterGraphics;

public abstract class GameObjectImpl implements GameObject {

    public GameObjectImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setHeight(final int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setWidth(final int width) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Position getPos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPos(final Position pos) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Velocity getVel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setVel(final Velocity vel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setSpeed(final double speed) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateInput(final ControllerInput controller) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) {
        // TODO Auto-generated method stub
        
    }

}
