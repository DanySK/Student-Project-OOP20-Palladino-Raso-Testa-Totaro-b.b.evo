package model.leaderboard;

public class PlayerImpl implements Player, Life {

    private static final int MAX_LIFE = 3;
    private final String alias;
    private int score;
    private int life;

    public PlayerImpl(final String alias, final int score, final int life) {
        this.alias = alias;
        this.score = score;
        this.life = life;
    }

    /**
     *  Method that allows to add or subtract the score to the player.
     *  @param value - represents the value that must be added or subtract to the score.
     *
     */
    @Override
    public void scoreOperation(final int value) {
        if (this.isAlive() && this.getScore() > 0) {
            this.score += value;
        }
    }

    /**
     *  Return a String that represents the alias of the player.
     *  @return a String that represents the alias of the player.
     *
     */
    @Override
    public String getAlias() {
        return this.alias;
    }

    /**
     *  Return an Integer that represents the value of the player score.
     *  @return an Integer that represents the value of the player score.
     *
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     *  Return an Integer that represents the value of the life.
     *  @return an Integer that represents the value of the life.
     *
     */
    @Override
    public int getLife() {
        return this.life;
    }

    /**
     *  Method that allows to add, of a one unit, the life.
     */
    @Override
    public void increaseLife() {
        if (this.getLife() < MAX_LIFE) {
            this.life++;
        }
    }

    /**
     *  Method that allows to subtract, of a one unit, the life.
     */
    @Override
    public void decreaseLife() {
        if (this.getLife() > 0) {
            this.life--;
        }
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
        return "[alias = " + this.alias + ", score = " + this.score + " ,life = " + this.life + "]";
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
