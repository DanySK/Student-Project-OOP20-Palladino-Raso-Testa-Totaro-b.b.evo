package controller.game;

import java.util.HashMap;
import java.util.Map;

import controller.input.ControllerInput;
import controller.menu.SceneLoader;
import controller.sound.SoundController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.GameBoard;
import model.entities.Paddle;
import model.mapeditor.LevelSelection;
import model.utilities.GameUtilities;
import paranoid.controller.fxmlcontroller.GameController;
import paranoid.view.layoutmanager.LayoutManager;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.game.ControllerGame;


public class GameLoop implements Runnable {

    private static final long PERIOD = 20;
    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;
    private final ControllerGame controllerGame;
    private final Map<Paddle, ControllerInput> inputController = new HashMap<>();
    private final SoundController sound;


    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();
        //this.sound = new SoundController(); da ragionarci
        //this.gameController = (GameController) LayoutManager.GAME.getGuiController();
        this.controllerGame = (ControllerGame) new FXMLLoader(getClass().getResource(PersonalViews.SCENE_GAME.getURL().getPath())).load();
        this.controllerGame.setBackgroundImage(gamestate.getLevel().getBackGround);
        //this.sound.setMusicEnable(gameState.isMusicActive());
        //this.sound.setEffectEnable(gameState.isEffectActive());
        //this.board.getEventHanlder().addMusicPlayer(player);
        //this.sound.playMusic(gameState.getLevel().getMusic());
        //this.changeView(LayoutManager.GAME);
        //this.inputController.put(Paddle, new KeyboardInputController());
        //final InputHandler inputHandler = new KeyboardInputHandler(this.inputController, this.gameController.getCanvas(), this.gameState);
            //inputHandler.notifyInputEvent();
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
                this.gameController.setPause(true);
                render();
                break;
            case RUNNING:
                this.gameController.setPause(false);
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
        sound.stopMusic();
        if (gameState.getPhase().equals(GamePhase.WIN)
                && LevelSelection.isStoryLevel(gameState.getLevel().getLevelName()) 
                && LevelSelection.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
                saveState(GamePhase.WIN);
            changeView(LayoutManager.NEXT_LEVEL);
        } else if (gameState.getPhase().equals(GamePhase.MENU)) {
            changeView(LayoutManager.MENU);
        } else { 
            saveState(GamePhase.LOST);
            changeView(LayoutManager.GAME_OVER);
        }
    }


    private void changeView(final LayoutManager layoutManager) {
        // TODO Auto-generated method stub
    }

    /**
     * stores the game progression as a checkpoint.
     * @param phase to set 
     */
    private void saveState(final GamePhase state) {
        final SettingsBuilder settingsBuilder = new SettingsBuilder();
        if (state.equals(GamePhase.WIN)) {
            UserManager.saveUser(gameState.getUser());
            SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                           .selectLevel(LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel())
                           .build());
        } else if (state.equals(GamePhase.LOST)) {
            UserManager.saveUser(new User());
            if (LevelSelection.isStoryLevel(gameState.getLevel().getLevelName())) {
                SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
                               .selectLevel(LevelSelection.LEVEL1.getLevel())
                               .build());
            }

        }
    }


    private void waitForNextFrame(final long current) {
        final long diffTime = System.currentTimeMillis() - current;
        if (diffTime < PERIOD) {
                try {
                    Thread.sleep(PERIOD - diffTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }


    private void updateGame(final int elapsed) {
        board.getEventHanlder().resolveEvent();
        board.updateState(elapsed);
    }


    private void processInput() {
        inputController.entrySet().forEach(i -> {
            board.movePaddle(i.getKey(), i.getValue());
        });
    }


    private void render() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameController.render(board.getSceneEntities(), gameState.getTopScores(), 
                                      gameState.getPlayerScore(), gameState.getLives());
            }
        });
    }

}
