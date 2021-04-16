
package model.entities;

import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.DirVector;
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
     * @return the direction of velocity
     */
    DirVector getDirVector();

    /**
     * @param dirVel to set
     */
    void setDirVector(DirVector dirVel);

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
     * @param board model
     */
    void updatePhysics(int timeElapsed, GameBoardImpl board);

    /**
     * @param controller of this gameObject
     */
    void updateInput(ControllerInput controller);

    /**
     * 
     * @param adapterGraphics to adapt the object to the screen
     */
    void updateGraphics(AdapterGraphics adapterGraphics);

}
