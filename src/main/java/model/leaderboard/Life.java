package model.leaderboard;

public interface Life {

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
}
