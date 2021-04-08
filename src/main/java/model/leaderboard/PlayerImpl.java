package model.leaderboard;

public class PlayerImpl implements Player {

    private final String alias;
    private int score;
    private int life;
    private int maxNumberOfLife;

    public PlayerImpl(final String alias, final int score, final int life, final int maxNumberOfLife) {
        this.alias = alias;
        this.score = score;
        this.life = life;
        this.maxNumberOfLife = maxNumberOfLife;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void scoreOperation(final int value) {
        if (this.isAlive() && this.getScore() > 0) {
            this.score += value;
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public String getAlias() {
        return this.alias;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public int getLife() {
        return this.life;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void increaseLife() {
        if (this.getLife() < this.getMaxNumberOfLife()) {
            this.life++;
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void decreaseLife() {
        if (this.getLife() > 0) {
            this.life--;
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public int getMaxNumberOfLife() {
        return this.maxNumberOfLife;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void setMaxNumberOfLife(final int value) {
        this.maxNumberOfLife = value;
    }

    /**
     *  Returns true if the value of life is major of 0.
     *  @return true if the value of life is major of 0.
     *
     */
    @Override
    public boolean isAlive() {
        return this.life > 0;
    }

    /**
     *  Return a String that represents the player object.
     *  @return a String that represents the player object.
     *
     */
    @Override
    public String toString() {
        return "[alias = " + this.alias + " ,score = " + this.score + " ,life = " + this.life 
             + " ,maxLife = " + this.maxNumberOfLife + "]";
    }

    /**
     *
     *  Returns the hash code value for this player.
     *  @return the hash code value for this player.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.alias == null) ? 0 : this.alias.hashCode());
        return result;
    }

    /**
     *  Method that compare the specified object whit the current player.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Player player = (Player) obj;

        return player.getAlias().equals(this.alias);
    }


}
