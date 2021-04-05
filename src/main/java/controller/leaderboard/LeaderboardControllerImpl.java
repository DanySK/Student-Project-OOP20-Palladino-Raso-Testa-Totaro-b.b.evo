package controller.leaderboard;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import controller.utilities.IOLeaderboard;
import model.leaderboard.Leaderboard;
import model.leaderboard.LeaderboardImpl;
import model.leaderboard.Player;

public class LeaderboardControllerImpl implements LeaderBoardController {

    private final Leaderboard leaderboard;

    public LeaderboardControllerImpl() {
        this.leaderboard = new LeaderboardImpl(IOLeaderboard.readLeaderboard());
    }

    @Override
    public void addPlayerInLeaderBoard(final Player player) {
        this.leaderboard.addPlayer(player.getAlias(), player.getScore());
    }

    @Override
    public Map<String, Integer> viewLeaderboard() {
        return Collections.unmodifiableMap(this.leaderboard.getLeaderBoard());
    }

    @Override
    public Map<String, Integer> getPoudium(final int index) {
        this.leaderboard.sortByScore();
        return this.leaderboard.getLeaderBoard()
                               .entrySet()
                               .stream()
                               .limit(index)
                               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void printLeaderBoard() {
        IOLeaderboard.printInJsonFormat(this.viewLeaderboard());
    }

}
