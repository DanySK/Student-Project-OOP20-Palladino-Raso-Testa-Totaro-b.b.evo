package model.utilities;

public enum Difficulty {

    /**
     * Use to set the number of lives of the player, the ball velocity 
     * and The value that will be multiplied to the score.
     */
    NORMAL(3, 0.4, 1),

    /**
     * Use to set the number of lives of the player, the ball velocity 
     * and The value that will be multiplied to the score.
     */
    HARD(1, 0.6, 3);

    private int numberOfLives;
    private double ballVelocity;
    private int multiplyScoreValue;

    Difficulty(final int numberOfLives, final double ballVelocity, final int multiplyScoreValue) {
        this.numberOfLives = numberOfLives;
        this.ballVelocity = ballVelocity;
        this.multiplyScoreValue = multiplyScoreValue;
    }

    public int getNumberOfLives() {
        return this.numberOfLives;
    }

    public double getBallVelocity() {
        return this.ballVelocity;
    }

    public int getMultiplyScoreValue() {
        return this.multiplyScoreValue;
    }

}
