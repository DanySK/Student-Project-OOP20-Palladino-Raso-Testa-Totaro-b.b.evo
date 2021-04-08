package controller.leaderboard;

import java.util.Map;

import model.leaderboard.Player;

public interface LeaderboardController {

    /**
     *  Method that allows to , add or overwrite, player in the ranking.
     *  @param player - Represent the player instance.
     */
    void addPlayerInLeaderBoard(Player player);

    /**
     *  Return a Map representing the Couple Alias - Score sorted by score.
     *  @return a Map representing the Couple Alias - Score sorted by score.
     *
     */
    Map<String, Integer> viewLeaderboard();

    /**
     *  Return a Map representing the Couple Alias - Score sorted by score that contains
     *  only n-index elements.
     *  @param index
     *  @return a Map representing the Couple Alias - Score sorted by score that contains only n-index elements.
     *
     *
     */
    Map<String, Integer> getPoudium(int index);

    /**
     *  Method that allows to print on file a sorted leaderboard.
     */
    void saveSortLeaderboard();

    /**
     *  Method that allows to delete leaderboard and print it on file.
     */
    void clearLeaderboard();
}
