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
        final var map = IOLeaderboard.readLeaderboard();
        if (map == null || map.isEmpty()) {
            this.leaderboard = new LeaderboardImpl();
        } else {
            this.leaderboard = new LeaderboardImpl(map);
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void addPlayerInLeaderBoard(final Player player) {
        this.leaderboard.addPlayer(player.getAlias(), player.getScore());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Map<String, Integer> viewLeaderboard() {
        return Collections.unmodifiableMap(this.leaderboard.getLeaderBoard());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Map<String, Integer> getPoudium(final int index) {
        this.leaderboard.sortByScore();
        System.out.println(this.leaderboard.getLeaderBoard());
        return this.leaderboard.getLeaderBoard()
                               .entrySet()
                               .stream()
                               .limit(index)
                               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void saveSortLeaderboard() {
        this.leaderboard.sortByScore();
        IOLeaderboard.printInJsonFormat(this.viewLeaderboard());
    }

}
