package model.leaderboard;

public class PlayerBuilderImpl implements PlayerBuilder {

    private static final long serialVersionUID = -7344646435858952139L;
    private static final int MAX_LIFE = 3;
    private String alias;
    private int score;
    private int life;

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder alias(final String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder score(final int score) {
        this.score = score;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder life(final int life) {
        this.life = life;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Player build() {
        if (this.alias == null 
            ||  this.score < 0 
            || this.life < 0 
            || this.life > MAX_LIFE) {
            throw new IllegalStateException();
        }
        return new PlayerImpl(this.alias, this.score, this.life);
    }

}
