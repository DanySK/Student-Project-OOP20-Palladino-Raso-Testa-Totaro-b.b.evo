package model.leaderboard;

import java.util.Map;

@FunctionalInterface
public interface LeaderboardSortingStrategy {

    Map<String, Integer> sortMap(Map<String, Integer> map);
}
