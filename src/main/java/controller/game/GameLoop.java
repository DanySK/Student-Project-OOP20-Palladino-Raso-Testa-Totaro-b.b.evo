package controller.game;

import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import controller.input.InputEvent;
import controller.input.InputEventImpl;
import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.GameBoard;
import model.mapeditor.LevelSelection;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.settings.SettingLevelManager;
import model.utilities.GameUtilities;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.game.ControllerGame;


public class GameLoop implements Runnable {

    private static final long PERIOD = 20;
    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;
    private final ControllerGame controllerGame;
    private final ControllerInput inputController;
    private final SettingsController setting = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.scene.getStylesheets().add(PersonalStyle.DEFAULT_STYLE.getStylePath()); //Apply css to scene
        final Stage currentStage = (Stage) this.scene.getWindow();
        currentStage.setResizable(false); // Don't permise resize
        currentStage.setWidth(GameUtilities.SCREEN_WIDTH); //Set new Dimension
        currentStage.setHeight(GameUtilities.SCREEN_HEIGHT); // Set new Dimension
        System.err.println("2)" + this.scene.getWindow()); //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();
        //System.out.println(SceneLoader.loadScene(ClassLoader.getSystemResource(PersonalViews.SCENE_GAME.getURL().getPath())));
        this.controllerGame = (ControllerGame) PersonalViews.SCENE_GAME.loadScene();
        this.controllerGame.setBackgroundImage(gameState.getLevel().getBackground());
        if (this.setting.isMusicEnable()) {
            SoundController.playMusic(gameState.getLevel().getMusic().getURL().getPath());
        }
        this.changeView(PersonalViews.SCENE_GAME);
        this.inputController = new ControllerInputImpl();
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
                System.out.println("dentro");
                gameState.init();
                break;
            case PAUSE:
                this.controllerGame.setPlay(false);
                //System.out.println("pausa");
                render();
                break;
            case RUNNING:
                this.controllerGame.setPlay(true);
                System.out.println("running");
                processInput();
                updateGame(elapsed);
                render();
                break;
            default:
                System.out.println("Oh cazz");
                break;
            }
            waitForNextFrame(current);
            lastTime = current;
        }
        SoundController.stopMusic();
        if (gameState.getPhase().equals(GamePhase.WIN)
                && LevelSelection.isStandardLevel(gameState.getLevel().getLevelName()) 
                && LevelSelection.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
                saveState(GamePhase.WIN);
            changeView(PersonalViews.SCENE_NEXT_LEVEL);
        } else if (gameState.getPhase().equals(GamePhase.MENU)) {
            changeView(PersonalViews.SCENE_MAIN_MENU);
        } else { 
            saveState(GamePhase.LOST);
            changeView(PersonalViews.SCENE_GAME_OVER);
        }
    }


    /**
     * Alessandro, probabilmente mi serve una funzione loader per caricare tutti gli fxml. Funzionera? .
     * @param layout
     */
    private void changeView(final PersonalViews layout) {
        if (layout.equals(PersonalViews.SCENE_NEXT_LEVEL)) {
            //final ControllerNextLevel nextLevelController = (ControllerNextLevel) SceneLoader.loadScene(PersonalViews.SCENE_NEXT_LEVEL.getURL());
            //nextLevelController.update(gameState.getLevel(), gameState.getPlayer());
        } else if (layout.equals(PersonalViews.SCENE_GAME_OVER)) {
            //final ControllerGameOver controllerGameOver = (GameOverController) SceneLoader.loadScene(PersonalViews.SCENE_GAME_OVER.getURL());
            //gameOverController.updateScore(gameState.getTopScores(), gameState.getUser(), gameState.getLevel());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //scene.setRoot(SceneLoader.loadParent(layout.getURL())); //Genera ERRRORE SceneLoader.loadParent(layout.getURL()
                scene.setRoot(PersonalViews.SCENE_GAME.getLayout());
                System.err.println("3)" + scene.getWindow());
                System.err.println("GameController : " + controllerGame); ///AAAAAAAAAAAAAAAAAAAAAAAA
            }
        });

    }

    /**
     * stores the game progression as a checkpoint.
     * @param phase to set 
     */
    private void saveState(final GamePhase state) {
        final SettingLevelBuilder levelLoader = new SettingLevelBuilder();
        if (state.equals(GamePhase.WIN)) {
            SettingLevelManager.saveOption(levelLoader.fromSettings(SettingLevelManager.loadOption())
                    .selectLevel(LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel())
                    .build());

        } else if (state.equals(GamePhase.LOST)) {
            //Ranking
            final LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
            leaderboard.addPlayerInLeaderBoard(null);
 
            if (LevelSelection.isStandardLevel(gameState.getLevel().getLevelName())) {
                SettingLevelManager.saveOption(levelLoader.fromSettings(SettingLevelManager.loadOption())
                    .selectLevel(LevelSelection.LEVEL1.getLevel())
                    .build());
            }
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
