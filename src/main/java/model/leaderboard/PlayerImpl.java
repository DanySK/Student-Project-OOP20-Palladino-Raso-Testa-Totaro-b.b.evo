package model.leaderboard;

public class PlayerImpl implements Player {

    private final String alias;
    private int score;
    private Life playerLife;

    public PlayerImpl(final String alias, final int score, final Life life) {
        this.alias = alias;
        this.score = score;
        this.playerLife = life;
    }

    @Override
    public void scoreOperation(final int value) {
        if (this.playerLife.isAlive()) {
            this.score += value;
        }
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return "[alias=" + alias + ", score=" + score + "]";
    }

    @Override
    public int getLife() {
        return this.playerLife.getLife();
    }

}
