package model.utilities;

/**
 * Represents the entities in the game with the corresponding value.
 *
 */
public enum GameObjectType {

    /**
     *  Represents a wall.
     */
    WALL('0'),
    /**
     *  Represents the ball.
     */
    BALL('1'),
    /**
     *  Represents a brick.
     */
    BRICK('2'),
    /**
     *  Represents the paddle.
     */
    PADDLE('3'),
    /**
     *  Represents the powerup.
     */
    POWERUP('4'),
    /**
     *  Represents the empty space.
     */
    EMPTY('5');
    private final char value;

    /**
     * This constructor permit to associate a GameObjectType with a value.
     * 
     * @param value
     */
    GameObjectType(final char value) {
        this.value = value;
    }

    /**
     * @return the value of the GameObject
     */
    public char getValue() {
        return value;
    }

    /**
     * @return a String for each GameObjectType
     */
    @Override
    public String toString() {
        switch (this) {
        case WALL:
            return "Wall";
        case BALL:
            return "Ball";
        case BRICK:
            return "Brick";
        case PADDLE:
            return "Paddle";
        case POWERUP:
            return "PowerUp";
        case EMPTY:
            return "Empty";
        default:
            return "";
        }
    }
}
