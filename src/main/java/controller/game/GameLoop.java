package controller.game;

import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import controller.input.InputEvent;
import controller.input.InputEventImpl;
import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.menu.SceneLoader;
import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.entities.GameBoard;
import model.leaderboard.LeaderboardSortingStrategy;
import model.leaderboard.Player;
import model.mapeditor.LevelSelection;
import model.utilities.GameUtilities;
import resource.routing.PersonalViews;
import view.game.ControllerGame;
import view.game.ControllerNextLevel;


public class GameLoop implements Runnable {

    private static final long PERIOD = 20;
    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;
    private final ControllerGame controllerGame;
    private final ControllerInput inputController = new ControllerInputImpl();
    private final SettingsController setting = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();
        this.controllerGame = (ControllerGame) SceneLoader.loadScene(PersonalViews.SCENE_GAME.getURL());
        this.controllerGame.setBackgroundImage(gameState.getLevel().getBackground());
        if (this.setting.isMusicEnable()) {
            SoundController.playMusic(gameState.getLevel().getMusic().getPath());
        }
        if (this.setting.isSoundFxEnable()) {
            SoundController.playSoundFx(null); // selezionare musica
        }
        this.changeView(PersonalViews.SCENE_GAME);
        final InputEvent inputEvent = new InputEventImpl(this.controllerGame.getCanvas(), inputController, this.gameState);
            inputEvent.notifyEvent();
    }


    /**
     * Apply the three game loop steps based on the game phase.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!gameState.getPhase().equals(GamePhase.WIN) 
            && !gameState.getPhase().equals(GamePhase.LOST)
            && !gameState.getPhase().equals(GamePhase.MENU)) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            switch (gameState.getPhase()) {
            case START:
                gameState.init();
                break;
            case PAUSE:
                this.controllerGame.setPlay(false);
                render();
                break;
            case RUNNING:
                this.controllerGame.setPlay(true);
                processInput();
                updateGame(elapsed);
                render();
                break;
            default:
                break;
            }
            waitForNextFrame(current);
            lastTime = current;
        }
        SoundController.stopMusic();
        if (gameState.getPhase().equals(GamePhase.WIN)
                && LevelSelection.isStandardLevel(gameState.getLevel().getLevelName()) 
                && LevelSelection.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
                //saveState(GamePhase.WIN);
            changeView(PersonalViews.SCENE_NEXT_LEVEL);
        } else if (gameState.getPhase().equals(GamePhase.MENU)) {
            changeView(PersonalViews.SCENE_MAIN_MENU);
        } else { 
            //saveState(GamePhase.LOST);
            changeView(PersonalViews.SCENE_GAME_OVER);
        }
    }


    /**
     * Alessandro, probabilmente mi serve una funzione loader per caricare tutti gli fxml.
     * @param layout
     */
    private void changeView(final PersonalViews layout) {
        if (layout.equals(PersonalViews.SCENE_NEXT_LEVEL)) {
            final ControllerNextLevel nextLevelController = (ControllerNextLevel) SceneLoader.loadScene(PersonalViews.SCENE_NEXT_LEVEL.getURL());
            nextLevelController.update(gameState.getLevel(), gameState.getPlayer());
        } else if (layout.equals(PersonalViews.SCENE_GAME_OVER)) {
            //final ControllerGameOver controllerGameOver = (GameOverController) SceneLoader.loadScene(PersonalViews.SCENE_GAME_OVER.getURL());
            //gameOverController.updateScore(gameState.getTopScores(), gameState.getUser(), gameState.getLevel());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scene.setRoot(SceneLoader.loadParent(layout.getURL()));
            }
        });
    }

    /**
     * stores the game progression as a checkpoint.
     * @param phase to set 
     */
    private void saveState(final GamePhase state) {
        final SettingsBuilder settingsBuilder = new SettingsBuilder();
        if (state.equals(GamePhase.WIN)) {
            //UserManager.saveUser(gameState.getPlayer());
            LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel();
            SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                           .selectLevel()
                           .build());
        } else if (state.equals(GamePhase.LOST)) {
            //Ranking
            LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
            leaderboard.addPlayerInLeaderBoard(null);
            //UserManager.saveUser(new User());

        }
    }

    private void waitForNextFrame(final long current) {
        final long timeElapsed = System.currentTimeMillis() - current;
        if (timeElapsed < PERIOD) {
                try {
                    Thread.sleep(PERIOD - timeElapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * @param elapsed
     */
    private void updateGame(final int elapsed) {
        board.getEventHanlder().manageEvent();
        board.updateState(elapsed);
    }


    /**
     * 
     */
    private void processInput() {
            board.movePaddle(inputController);
    }

    /**
     * 
     */
    private void render() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controllerGame.render(board.getSceneEntities(), gameState.getPlayerScore(), gameState.getLives());
            }
        });
    }
}
