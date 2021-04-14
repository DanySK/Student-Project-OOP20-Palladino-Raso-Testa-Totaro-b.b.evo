package controller.game;

import java.io.IOException;
import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import controller.input.InputEvent;
import controller.input.InputEventImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.entities.GameBoard;
import model.mapeditor.LevelSelection;
import resource.routing.PersonalViews;
import view.game.ControllerGame;


public class GameLoop implements Runnable {

    /* Alex in pratica devi vedere come gestire il sound perche non so come implementare la tua roba
    * e poi vedere la funzione SaveState come salvare i progressi nei setting, perche li in base a se vinci o se perdi vai al livello successivo/ 
    * torni al precendete io la mia parte che sapevo come implementare l'ho fatta,
    *  adesso bisogna vedere come gestisci tu i setting, dovresti salvarti i progessi e poterli ricaricare.
    * 
    */

    private static final long PERIOD = 20;
    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;
    private final ControllerGame controllerGame;
    private final ControllerInput inputController = new ControllerInputImpl();
    //private final SoundController sound; //Alex

    public GameLoop(final Scene scene) throws IOException {
        this.scene = scene;
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();
        //this.sound = new SoundController();
        this.controllerGame = (ControllerGame) new FXMLLoader(getClass().getResource(PersonalViews.SCENE_GAME.getURL().getPath())).load();
        this.controllerGame.setBackgroundImage(gameState.getLevel().getBackground());
        //this.sound.setMusicEnable(gameState.isMusicActive());
        //this.sound.setEffectEnable(gameState.isEffectActive());
        //this.board.getEventHanlder().addMusicPlayer(sound);
        //this.sound.playMusic(gameState.getLevel().getMusic());
        this.changeView(PersonalViews.SCENE_GAME);
        final InputEvent inputEvent = new InputEventImpl(this.controllerGame.getCanvas(), inputController, this.gameState);
            inputEvent.notifyAll();
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
        //sound.stopMusic();
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
     * Alessandro, probabilmente mi serve una funzione loader per caricare tutti gli fxml.
     * @param layout
     */
    private void changeView(final PersonalViews layout) {
        if (layout.equals(PersonalViews.SCENE_NEXT_LEVEL)) {
            final NextLevelController nextLevelController = (NextLevelController) new FXMLLoader(getClass().getResource(PersonalViews.SCENE_NEXT_LEVEL.getURL().getPath())).load();
            //nextLevelController.update(gameState.getLevel(), gameState.getUser());
        } else if (layout.equals(PersonalViews.SCENE_GAME_OVER)) {
            final GameOverController gameOverController = (GameOverController) new FXMLLoader(getClass().getResource(PersonalViews.SCENE_GAME_OVER.getURL().getPath())).load();
            //gameOverController.updateScore(gameState.getTopScores(), gameState.getUser(), gameState.getLevel());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scene.setRoot(new FXMLLoader(getClass().getResource(PersonalViews.SCENE_GAME.getURL().getPath())).load());
            }
        });
    }

    /**
     * Alex, non capisco come gestisci il setting, in sostanza qui devi caricare il prossimo livello se completi il primo
     * mentre se perdi devi selezionare il livello 1
     * DA GUARDARE
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
            if (LevelSelection.isStandardLevel(gameState.getLevel().getLevelName())) {
                SettingsManager.saveOption(settingsBuilder.fromSettings(SettingsManager.loadOption())
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
                controllerGame.render(board.getSceneEntities(), gameState.getTopScores(), 
                                      gameState.getPlayerScore(), gameState.getLives());
            }
        });
    }
}
