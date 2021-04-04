package model.entities;

import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.Velocity;
import view.graphics.AdapterGraphics;

public interface GameObject {

    /**
     * @param height to set
     */
    void setHeight(int height);

    /**
     * @param width to set
     */
    void setWidth(int width);

    /**
     * @return the height
     */
    int getHeight();

    /**
     * @return the width
     */
    int getWidth(); 

    /**
     * @return the position
     */
    Position getPos();

    /**
     * @param pos to set
     */
    void setPos(Position pos);

    /**
     * @return the velocity
     */
    Velocity getVel();

    /**
     * @param vel to set
     */
    void setVel(Velocity vel);

    /**
     * @param speed to set
     */
    void setSpeed(double speed);

    /**
     * @return the speed
     */
    double getSpeed();

    /**
     * @param timeElapsed from game loop
     * @param world model
     */
    void updatePhysics(int timeElapsed, GameBoardImpl world);

    /**
     * @param controller of this gameObject
     */
    void updateInput(ControllerInput controller);

    /**
     * 
     * @param graphicsAdapter to adapt the object to the screen
     */
    void updateGraphics(AdapterGraphics graphicsAdapter);

}
