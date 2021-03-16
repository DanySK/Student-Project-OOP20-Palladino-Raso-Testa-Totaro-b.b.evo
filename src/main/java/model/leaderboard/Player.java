package model.leaderboard;

public interface Player {

    void scoreOperation(int value);

    String getAlias();

    int getScore();

    int getLife();
}
