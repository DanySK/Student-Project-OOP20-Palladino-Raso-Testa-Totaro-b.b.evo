package model.leaderboard;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public interface Leaderboard {

    /**
     *  Method that allows to sort by score, the ranking.
     */
    void sortByScore();

    /**
     *  Method that allows to , add or overwrite, player in the ranking.
     *  @param alias - Represent the canonical name of the player.
     *  @param score - Represent the score name of the player.
     */
    void addPlayer(String alias, int score);

    /**
     *  Method that allows to remove player in the ranking.
     *  @param alias - Represent the canonical name of the player.
     *  @param score - Represent the score name of the player.
     *  @exception IllegalArgumentException - If the player is not present in the ranking
     */
    void removePlayer(String alias, int score);

    /**
     *  Return a Map representing the Couple Alias - Score not sorted.
     *  @return a Map representing the Couple Alias - Score not sorted.
     *
     */
    Map<String, Integer> getLeaderBoard();

    /**
     *  Return a Entry representing the first place player in the ranking.
     *  @return a Entry representing the first place player in the ranking or Optional empty.
     *
     */
    Optional<Entry<String, Integer>>  getTopPlayer();

    /**
     *  Return a Entry representing the second place player in the ranking.
     *  @return a Entry representing the second place player in the ranking or Optional empty.
     *
     */
    Optional<Entry<String, Integer>>  getSecondPlayer();

    /**
     *  Return a Entry representing the third place player in the ranking.
     *  @return a Entry representing the third place player in the ranking or Optional empty.
     *
     */
    Optional<Entry<String, Integer>>  getThirdPlayer();
}
