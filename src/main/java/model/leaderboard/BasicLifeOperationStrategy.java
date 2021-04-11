package model.leaderboard;

public class BasicLifeOperationStrategy implements LifeOperationStrategy {

    @Override
    public int lifeOperation(final int currentLife, final int value, final int maxLife) {
        int result = currentLife + value;
        if (result > maxLife) {
            result = maxLife;
        }
        if (result < 0) {
            result = 0;
        }
        return result;
    }

}
