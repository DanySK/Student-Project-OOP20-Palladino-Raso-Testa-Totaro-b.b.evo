package model.leaderboard;

public interface Player {

    /**
     *  Method that allows to add or subtract the score to the player.
     *  @param value - represents the value that must be added or subtract to the score.
     *
     */
    void scoreOperation(int value);

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
