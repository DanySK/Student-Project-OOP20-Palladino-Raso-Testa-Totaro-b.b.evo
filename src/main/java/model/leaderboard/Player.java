package model.leaderboard;

public interface Player {

    void increaseScore(int value);

    void decreaseScore(int value);

    String getAlias();

    int getScore();
}
