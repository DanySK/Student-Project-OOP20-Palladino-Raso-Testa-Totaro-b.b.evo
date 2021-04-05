package controller.leaderboard;

import java.util.Map;

import model.leaderboard.Player;

public interface LeaderBoardController {

    void addPlayerInLeaderBoard(Player player);

    Map<String, Integer> viewLeaderboard();

    Map<String, Integer> getPoudium(int index);

    void saveSortLeaderboard();
}
