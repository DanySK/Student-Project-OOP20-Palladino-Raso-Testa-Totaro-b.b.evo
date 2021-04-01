package model.utilities;

public enum ScoreAttribute {

    /**
     * Used to represent the value that must be added to the score when the wall breaks.
     */
    BREAK_WALL(+80),

    /**
     * Used to represent the value that must be added to the score when the wall harms.
     */
    HARM_WALL(+40),

    /**
     * Used to represent the value that must be added to the score
     * when the player take a positive powerup.
     */
    POSITIVE_POWERUP(+40),

    /**
     * Used to represent the value that must be added to the score
     * when the player take a negative powerup.
     */
    NEGATIVE_POWERUP(-80),

    /**
     * Used to represent the value that must be added to the score
     * when the player lost life.
     */
    LOST_LIFE(-10),

    /**
     * Used to represent the value that must be added to the score
     * when the player take life.
     */
    INCREASE_LIFE(+10);

    private int value;

    ScoreAttribute(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
