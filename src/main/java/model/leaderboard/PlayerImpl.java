package model.leaderboard;

public class PlayerImpl implements Player {

    private final String alias;
    private int score;

    public PlayerImpl(final String alias, final int score) {
        this.alias = alias;
        this.score = score;
    }
    @Override
    public void increaseScore(final int value) {
        this.score += value;
    }

    @Override
    public void decreaseScore(final int value) {
        this.score -= value;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public int getScore() {
        return this.score;
    }

}
