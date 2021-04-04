package controller.game;

import model.entities.GameBoard;
import model.leaderboard.Player;
import model.mapeditor.Level;
import model.utilities.Difficulty;

public class GameStateImpl implements GameState{

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPlayerScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getHighScoreValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addPoint(int point) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addBonus() {
        // TODO Auto-generated method stub

    }

    @Override
    public void flatMultiplier() {
        // TODO Auto-generated method stub

    }

    @Override
    public void incMultiplier() {
        // TODO Auto-generated method stub

    }

    @Override
    public void decLives() {
        // TODO Auto-generated method stub

    }

    @Override
    public void incLives() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLives() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public GameBoard getBoard() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GamePhase getPhase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPhase(GamePhase phase) {
        // TODO Auto-generated method stub

    }

    @Override
    public Player getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getTopScores() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Level getLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Difficulty getDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMusicActive() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEffectActive() {
        // TODO Auto-generated method stub
        return false;
    }

}
