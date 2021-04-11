package model.leaderboard;

@FunctionalInterface
public interface ScoreOperationStrategy {

    int scoreOperation(int currentScore, int value);
}
