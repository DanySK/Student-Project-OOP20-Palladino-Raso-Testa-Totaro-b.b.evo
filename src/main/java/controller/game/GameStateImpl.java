package controller.game;

import java.util.Arrays;

import controller.BrickBreakerEvo;
import controller.settings.SettingsControllerImpl;
import model.entities.Ball;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.leaderboard.Player;
import model.leaderboard.PlayerImpl;
import model.mapeditor.Level;
import model.mapeditor.LevelSelection;
import model.settings.GameSettingsImpl;
import model.utilities.ObjectInit;
import model.utilities.Wall;
import resource.routing.BackGround;
import model.utilities.Difficulty;
import model.utilities.GameUtilities;
import model.utilities.Angle;

public class GameStateImpl implements GameState {

    //dovrebbe essere quasi tutto a posto, bisognera inserire i test e i controlli per tutti i metodi pero
    private GamePhase phase;
    private int multiplier;
    private final GameBoard board;
    private final Level level;
    private final Player player;
    private final String settingFilePath = BrickBreakerEvo.SETTINGS_FOLDER + ".settings.json ";
    private final SettingsControllerImpl setting;

    public GameStateImpl() {
        this.phase = GamePhase.START;
        this.level = new Level(null/*brick*/, LevelSelection.LEVEL1, null/*music*/, BackGround.BACKGROUND_1);
        this.player = new PlayerImpl(player.getAlias(), 0, player.getLife(), player.getMaxNumberOfLife()); //Li dovrei prendere da GameController
        this.setting = new SettingsControllerImpl(settingFilePath);
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
     * Secondo me puo essere eliminato.
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

    //DA GUARDARE PERCHE AVENDO IL SETTING CONTROLLER A QUESTO PUNTO POTREBBE NON SERVIRE
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
