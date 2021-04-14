package controller.game;

import java.util.Arrays;

import controller.BrickBreakerEvo;
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
import model.mapeditor.LevelSelection;
import model.utilities.ObjectInit;
import model.utilities.Difficulty;
import model.utilities.GameUtilities;
import model.utilities.Angle;

public class GameStateImpl implements GameState {

    /*
     * Alex qui dovresti vedere se implementare o meno i Setting e il Player, e come implementare poi tutti i vari metodi,
     * probabilmente non devi implementare molto pero io non ho idea di come fare visto che comunque e roba che hai gestito tu a modo tuo
     */
    private GamePhase phase;
    private int multiplier;
    private final GameBoard board;
    private final Level level;
    private final PlayerImpl player; //Alex
    private final String settingFilePath = BrickBreakerEvo.SETTINGS_FOLDER + ".settings.json "; //da cambiare gameutilities
    private final SettingsControllerImpl setting; //Alex

    public GameStateImpl() {
        this.phase = GamePhase.START;
        this.level = LevelSelection.LEVEL1.getLevel();
        this.setting = new SettingsControllerImpl(settingFilePath); 
        this.player = new PlayerImpl("Player", 0, setting.getDifficulty().getNumberOfLives(), 3); // per le max life ci vuole metodo
        this.board = new GameBoardImpl(new Wall(GameUtilities.WORLD_WIDTH, GameUtilities.WORLD_HEIGHT), this);
        this.board.setBricks(level.getBricks());
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
                                         .build());
        this.board.setBalls(Arrays.asList(new Ball.Builder()
                                             .position(ObjectInit.BALL.getStartPos())
                                             .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                                             .height(ObjectInit.BALL.getInitHeight())
                                             .width(ObjectInit.BALL.getInitWidth())
                                             .speed(setting.getDifficulty().getBallVelocity())
                                             .build()));
        this.phase = GamePhase.PAUSE;
    }

    /**
     * 
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
     * Alex.
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
     * Alex.
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Alex.
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
     * Alex.
     * {@inheritDoc}
     */
    @Override
    public Difficulty getDifficulty() {
        return setting.getDifficulty();
    }

    /**
     * Alex.
     * {@inheritDoc}
     */
    @Override
    public boolean isMusicActive() {
        return setting.isMusicEnable();
    }

    /**
     * Alex.
     * {@inheritDoc}
     */
    @Override
    public boolean isEffectActive() {
        return setting.isSoundFxEnable();
    }

}
