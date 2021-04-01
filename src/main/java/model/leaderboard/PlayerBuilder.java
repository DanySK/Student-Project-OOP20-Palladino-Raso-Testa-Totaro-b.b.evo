package model.leaderboard;

import java.io.Serializable;

public interface PlayerBuilder extends Serializable {

    /**
     * Used to set the player's alias, alias can be contain all type of words.
     * @param alias
     * @return the alias property.
     */
    PlayerBuilder alias(String alias);

    /**
     * Used to set the player's score.
     * @param score
     * @return the score property.
     */
    PlayerBuilder score(int score);

    /**
     * Used to set the player's life.
     * @param life
     * @return the life property.
     */
    PlayerBuilder life(int life);

    /**
     * Used to build a correct version of Player Class.
     * @return a correct version of Player whit validation data.
     */
    Player build();
}
