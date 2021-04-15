package controller.game;

import java.util.Arrays;
import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.settings.SettingsControllerImpl;
import model.entities.Ball;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.entities.Wall;
import model.leaderboard.Player;
import model.leaderboard.PlayerImpl;
import model.mapeditor.Level;
import model.settings.SettingLevel;
import model.settings.SettingLevelManager;
import model.utilities.ObjectInit;
import model.utilities.Difficulty;
import model.utilities.GameUtilities;
import model.utilities.Angle;

public class GameStateImpl implements GameState {

    private GamePhase phase;
    private int multiplier;
    private final GameBoard board;
    private final Level level;
    private final PlayerImpl player;
    private final SettingsControllerImpl setting;

    public GameStateImpl() {
        this.phase = GamePhase.START;
        this.setting = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH); 
        final SettingLevel settingLevel =  SettingLevelManager.loadOption();
        this.level = settingLevel.getSelectedLevel();
        this.player = new PlayerImpl(this.getPlayerAlias(), setting.getDifficulty().getInitialScore(), 
                                     setting.getDifficulty().getNumberOfLives(), 
                                     setting.getDifficulty().getMaxNumberOfLife());
        this.board = new GameBoardImpl(new Wall(GameUtilities.WORLD_WIDTH, GameUtilities.WORLD_HEIGHT), this);
        this.board.setBricks(level.getBricks());
    }

    /**
     * This method allow to get the alias for the partial player.
     * Saved in ranking whit alias zero.
     */
    private String getPlayerAlias() {
        final LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
        return leaderboard.viewLeaderboard()
                          .entrySet()
                          .stream()
                          .filter(x -> x.getValue() == 0)
                          .map(x -> x.getKey())
                          .findFirst()
                          .get();
    }

    /**
     *
     */
    @Override
    public void init() {
        this.baseMultiplier();
        final Paddle.Builder paddleBuilder = new Paddle.Builder();
        this.board.setPaddle(paddleBuilder.position(ObjectInit.PADDLE.getStartPos())
                                         .width(ObjectInit.PADDLE.getInitWidth())
                                         .height(ObjectInit.PADDLE.getInitHeight())
                                         .texturePath(level.getPaddleTexture().getPath())
                                         .build());
        this.board.setBalls(Arrays.asList(new Ball.Builder()
                                             .position(ObjectInit.BALL.getStartPos())
                                             .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                                             .height(ObjectInit.BALL.getInitHeight())
                                             .width(ObjectInit.BALL.getInitWidth())
                                             .speed(setting.getDifficulty().getBallVelocity())
                                             .path(level.getBallTexture().getPath())
                                             .build()));
        this.phase = GamePhase.PAUSE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerScore() {
        return player.getScore(); 
    }

    /**
     * {@inheritDoc}
     */
    public void baseMultiplier() {
        this.multiplier = this.setting.getDifficulty().getMultiplyScoreValue();
    }

    /**
     *
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
     *
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
    public Level getLevel() {
        return this.level;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Difficulty getDifficulty() {
        return setting.getDifficulty();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isMusicActive() {
        return setting.isMusicEnable();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isEffectActive() {
        return setting.isSoundFxEnable();
    }

}
