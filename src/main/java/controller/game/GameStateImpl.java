package controller.game;

import java.util.Arrays;

import controller.settings.SettingsControllerImpl;
import model.entities.Ball;
import model.entities.GameBoard;
import model.leaderboard.Player;
import model.leaderboard.PlayerImpl;
import model.mapeditor.Level;
import model.utilities.ConstantScreen;
import model.utilities.Difficulty;
import model.utilities.Angle;

public class GameStateImpl implements GameState {

    //dovrebbe essere quasi tutto a posto, bisognera inserire i test e i controlli per tutti i metodi pero
    private GamePhase phase;
    private final GameBoard board;
    private final Level level;
    private final Player player;
    private final SettingsControllerImpl setting;

    public GameStateImpl(final String alias, final int life) {
        this.phase = GamePhase.INIT;
        this.player = new PlayerImpl(alias, 0, life);
        this.setting = new SettingsControllerImpl();
        this.board = new GameBoardImpl(new Border(ConstantScreen.WORLD_WIDTH, ConstantScreen.WORLD_HEIGHT), this); // da sistemare
        this.board.setBricks(level.getBricks());
    }

    /**
     *
     */
    @Override
    public void init() {
        this.board.setBalls(Arrays.asList(new Ball.Builder()
                                             .position(Start.BALL.getSpawnPoint())
                                             .direction(Angle.EDGE_LEFT.getVector().mul(-1)) //da sistemare
                                             .height(Start.BALL.getInitHeight())
                                             .width(Start.BALL.getInitWidth())
                                             .speed(setting.getDifficulty().getSpeed()) //da sistemare
                                             .build()));
        this.phase = GamePhase.PAUSE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerScore() {
        return player.getScore();    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoint(final int point) {
        player.scoreOperation(point);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decLives() {
        player.decreaseLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incLives() {
        player.increaseLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return player.getLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBoard getBoard() {
        return board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GamePhase getPhase() {
        return this.phase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPhase(final GamePhase phase) {
        this.phase = phase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTopScores() {
        return player.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level getLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Difficulty getDifficulty() {
        return setting.getDifficulty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMusicActive() {
        return setting.isMusicEnable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEffectActive() {
        return setting.isSoundFxEnable();
    }

}
