package model.leaderboard;

@FunctionalInterface
public interface LifeOperationStrategy {

    int lifeOperation(int currentLife, int value, int maxLife);
}
