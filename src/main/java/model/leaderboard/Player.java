package model.leaderboard;

public interface Player {

    /**
     *  Method that allows to add or subtract the score to the player.
     *  @param value - represents the value that must be added or subtract to the score.
     *
     */
    void scoreOperation(int value);

    /**
     *  Return an Integer that represents the value of the life.
     *  @return an Integer that represents the value of the life.
     *
     */
    int getLife();

    /**
     *  Method that allows to add, of a one unit, the life.
     */
    void increaseLife();

    /**
     *  Method that allows to subtract, of a one unit, the life.
     */
    void decreaseLife();

    /**
     *  Returns true if the value of life is major of 0.
     *  @return true if the value of life is major of 0.
     *
     */
    boolean isAlive();

    /**
     *  Return a String that represents the alias of the player.
     *  @return a String that represents the alias of the player.
     *
     */
    String getAlias();

    /**
     *  Return an Integer that represents the value of the player score.
     *  @return an Integer that represents the value of the player score.
     *
     */
    int getScore();

}
