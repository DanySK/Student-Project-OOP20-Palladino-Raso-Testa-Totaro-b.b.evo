package model.utilities;
import java.util.Arrays;
import java.util.List;

public enum Direction { 
    /* directions types */
    UP,
    UP_LEFT,
    UP_RIGHT,
    DOWN,
    DOWN_LEFT,
    DOWN_RIGHT,
    LEFT,
    RIGHT;
    
    public static List<Direction> directionList(Direction d){
        return Arrays.asList(Direction.values());
    }
    
    /* this method returns a string for each direction */
    @Override
    public String toString() {
        switch (this) {
        case UP:
            return "Up";
        case UP_LEFT:
            return "Up-left";
        case UP_RIGHT:
            return "Up-right";
        case DOWN:
            return "Down";
        case DOWN_LEFT:
            return "Down-left";
        case DOWN_RIGHT:
            return "Down-Right";
        case LEFT:
            return "Left";
        case RIGHT:
            return "Right";
        default:
            return "";
        }
    }
    
}
