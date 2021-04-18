package controller.game;

import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import controller.input.InputEvent;
import controller.input.InputEventImpl;
import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.scene.ControllerGame;
import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.GameBoard;
import model.leaderboard.StandardScoreSortingStrategy;
import model.mapeditor.LevelSelection;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.settings.SettingLevelManager;
import model.utilities.GameUtilities;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.SceneLoader;
import view.game.ControllerNextLevel;
import view.gameover.GameOverController;


public class GameLoop implements Runnable {

    private static final long PERIOD = 20;
    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;
    private ControllerGame controllerGame;
    private final ControllerInput inputController;
    private final SettingsController setting = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.scene.getStylesheets().add(PersonalStyle.DEFAULT_STYLE.getStylePath()); //Apply css to scene
        final Stage currentStage = (Stage) this.scene.getWindow();
        currentStage.setResizable(false); // Don't permise resize
        currentStage.setWidth(GameUtilities.SCREEN_WIDTH); //Set new Dimension
        currentStage.setHeight(GameUtilities.SCREEN_HEIGHT); // Set new Dimension
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();
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
                gameState.init();
                break;
            case PAUSE:
                this.controllerGame.setPlay(true);
                render();
                break;
            case RUNNING:
                this.controllerGame.setPlay(false);
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
                saveState(GamePhase.WIN);
            changeView(PersonalViews.SCENE_NEXT_LEVEL); //sali al prossimo livello
        } else if (gameState.getPhase().equals(GamePhase.WIN) && gameState.getLevel().getLevelName().equals(LevelSelection.LEVEL6.getName())) {
            saveState(GamePhase.LOST);
            changeView(PersonalViews.SCENE_GAME_FINAL); //completato il gioco
        } else if (gameState.getPhase().equals(GamePhase.MENU)) { //nel caso premi esc
            changeView(PersonalViews.SCENE_MAIN_MENU);
        } else { 
            //controllo creative mode nel caso vinci o perdi
            if (GameStateImpl.isCreativeMode() && gameState.getPhase().equals(GamePhase.WIN)) {
                saveState(GamePhase.LOST);
                changeView(PersonalViews.SCENE_GAME_FINAL);
            } else { //perdi in tutti i casi
                saveState(GamePhase.LOST);
                changeView(PersonalViews.SCENE_GAME_OVER);
            }
        }
    }


    /**
     * 
     * @param layout
     */
    private void changeView(final PersonalViews layout) {
        if (layout.equals(PersonalViews.SCENE_NEXT_LEVEL)) {
            //da vedere perche carica male ed esce null
            final ControllerNextLevel nextLevelController = (ControllerNextLevel) layout.loadScene();
            nextLevelController.update(gameState.getLevel(), gameState.getPlayer());
        } else if (layout.equals(PersonalViews.SCENE_GAME_OVER)) {
            final GameOverController gameOverController = (GameOverController) layout.loadScene();
            final LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
            final StandardScoreSortingStrategy ls = new StandardScoreSortingStrategy(); 
            gameOverController.updateScore(this.gameState.getPlayerScore(), leaderboard.getPodium(0, ls).toString());
        } else if (layout.equals(PersonalViews.SCENE_GAME_FINAL)) {
            System.out.println("Vinto!");
            //DA IMPLEMENTARE GRAFICA PER DIRE CHE HAI VINTO E FINITO IL GIOCO
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //rimosso il confronto di url, ma solo del personal view, tanto dovrebbero coincidere no ?
                //come prima spotbug dava errore, cosi no invece
                if (layout.equals(PersonalViews.SCENE_GAME) || layout.equals(PersonalViews.SCENE_NEXT_LEVEL)) {
                    scene.setRoot(layout.getLayout());
                } else { // a cosa serve l'else?
                    SceneLoader.switchScene((Stage) scene.getWindow(), PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                            PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                            scene.getWidth(), 
                                            scene.getHeight(), 
                                            PersonalStyle.DEFAULT_STYLE.getStylePath());
                    scene.setRoot(null);
                }
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
            System.out.println(gameState.getLevel() + "163 loop");
            SettingLevelManager.saveOption(levelLoader.selectLevel(LevelSelection.getSelectionFromLevel(gameState.getLevel()).next().getLevel())
                    .build());
            System.out.println(gameState.getLevel() + "166 loop");
        } else if (state.equals(GamePhase.LOST)) {
            //Ranking
            final LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
            leaderboard.addPlayerInLeaderBoard(gameState.getPlayer());
            leaderboard.saveSortLeaderboard(new StandardScoreSortingStrategy());
 
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
