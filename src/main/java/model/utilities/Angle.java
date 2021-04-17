package model.utilities;
import java.util.Arrays;
import java.util.List;

public enum Angle { 
    /**
     * Used to represent the left edge of the paddle.
     */
    LEFT(150),
    /**
     * Used to represent the middle-left portion of the paddle.
     */
    MIDDLE_LEFT(140),
    /**
     * Used to represent the middle-right portion of the paddle.
     */
    MIDDLE_RIGHT(50),
    /**
     * Used to represent the right edge of the paddle.
     */
    RIGHT(30);

    private int angle;
    private static int versor = 1;

    Angle(final int angle) {
        this.angle = angle;
    }

    /**
     * Used to return the direction list.
     * @return List<Angle>
     * @param dir 
     * 
     */
    public static List<Angle> directionList(final Angle dir) {
        return Arrays.asList(Angle.values());
    }

    /**
     * Used to calculate the angle hit by the ball.
     * @return DirVector
     */
    public DirVector getAngleVector() {
        return new DirVector(Math.cos(Math.toRadians(angle)) * versor, Math.sin(Math.toRadians(angle)) * versor);
    }
}
