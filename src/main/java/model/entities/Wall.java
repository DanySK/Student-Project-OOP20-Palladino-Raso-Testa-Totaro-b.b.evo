
package model.entities;

import model.utilities.GameUtilities;
import model.utilities.Position;

public class Wall {

    private final double width;
    private final double height;

    public Wall(final double worldWidth, final double worldHeight) {
        this.width = worldWidth;
        this.height = worldHeight;
    }

    /**
     * 
     * @return width of the wall
     */
    public double getWidth() {
        return width;
    }

    /**
     * 
     * @return height of the wall
     */
    public double getHeight() {
        return height;
    }

    /**
     * 
     * @return the upper left corner
     */
    public Position getBottomRightCorner() {
        return GameUtilities.getRightBottomCorner();
    }

    /**
     * 
     * @return the bottom right corner
     */
    public Position getUpperleftCorner() {
        return GameUtilities.getUpperLeftCorner();
    }
}
