package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import controller.menu.SceneLoaderSingleton;
import controller.utilities.IOLeaderboard;
import controller.utilities.MapGame;
import javafx.application.Application;
import javafx.stage.Stage;
import main.java.it.unibo.pacman.controller.Launcher;
import main.java.it.unibo.pacman.controller.utilities.LeaderboardIO;
import main.java.it.unibo.pacman.controller.utilities.MapIO;
import main.java.it.unibo.pacman.model.utilities.Difficulty;
import main.java.it.unibo.pacman.view.MainMenuView;
import view.utilities.PersonalViews;

/**
 * PARANOID MAIN.
 * Stage creation.
 *
 */
public class BrickBreakerEvo extends Application {

    private static final String TITLE = "BRICK-BREAKER-EVO";
    private static final String STANDARD_MAP_PATH = "Game/Maps/standard.txt";
    private static String map = "";

    private static final int MIN_WIDHT = 450;
    private static final int MIN_HEIGHT = 550;

    /**
     * 
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        /*Get screen size */
        final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        SceneLoaderSingleton.switchScene(primaryStage, 
                PersonalViews.SCENE_MAIN_MENU.getURL(), 
                PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                graphicsDevice.getDisplayMode().getWidth() / 2, 
                graphicsDevice.getDisplayMode().getHeight() / 2);

        primaryStage.setMinWidth(MIN_WIDHT);
        primaryStage.setMinHeight(MIN_HEIGHT);
    }

    /**
     * initializes the game.
     * @param args command input
     */
    public static void main(final String[] args) {
        initSoftware();

        launch();
    }

    /**
     * 
     * if not present, create the folder to keep the game files.
     */
    private static void initSoftware() throws IOException {
        final BrickBreakerEvo launcher = new BrickBreakerEvo();
        if (new File(MapGame.getPath()).mkdirs()) {
            new File(MapGame.getPath() + "standard.txt");
            launcher.createStandardMap();
            MapGame.writeMap("standard", map);
        }
        if (new File(IOLeaderboard.getPath()).mkdirs()) {
            if (new File(IOLeaderboard.getPath() + "Ranking_" + Difficulty.HARD.toString() + ".txt").createNewFile()) {
                System.out.println("Hard ranking successfully created");
            }
            if (new File(IOLeaderboard.getPath() + "Ranking_" + Difficulty.NORMAL.toString() + ".txt").createNewFile()) {
                System.out.println("Normal ranking successfully created");
            }
        }
        new MainMenuView(TITLE).show();
    }

    private void createStandardMap() throws IOException {
        final Optional<BufferedReader> content = Optional.of(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(STANDARD_MAP_PATH))));
        final int width = Integer.parseInt(content.get().readLine());
        final int height = Integer.parseInt(content.get().readLine());
        map = map.concat(Integer.toString(width) + "\n" + Integer.toString(height) + "\n");
        String current;
        for (int i = 0; i < height; i++) {
            current = content.get().readLine();
            map = map.concat(current + "\n");
        }
        content.get().close();
    }

}
