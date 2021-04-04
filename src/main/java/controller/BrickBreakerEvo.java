package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import controller.menu.SceneLoaderSingleton;
import javafx.application.Application;
import javafx.stage.Stage;
import view.utilities.PersonalViews;

/**
 * BRICK-BREAKER-EVO MAIN.
 * Stage creation.
 *
 */
public class BrickBreakerEvo extends Application {

    /**
     * User's home folder.
     */
    public static final String HOME = System.getProperty("user.home");

    /**
    * identifies how the operating system separates files.
    */
    public static final String SEP = System.getProperty("file.separator");

    /**
     * Game folder destination.
     */
    public static final String MAIN_FOLDER = HOME + SEP + ".BrickBreakerEvo";

    /**
     * Folder where levels are saved.
     */
    public static final String LEVEL_FOLDER = MAIN_FOLDER + SEP + "Levels";

    /**
     * Folder where settings are saved.
     */
    private static final String SETTINGS_FOLDER = MAIN_FOLDER + SEP + "Settings" + SEP;

    /**
     * Folder where maps are saved.
     */
    private static final String MAPS_FOLDER = MAIN_FOLDER + SEP + "Maps";

    /**
     * Folder where Leaderboards are saved.
     */
    private static final String LEADERBOARDS_FOLDER = MAIN_FOLDER + SEP + "Leaderboards" + SEP;


    private static final int MIN_WIDHT = 450;
    private static final int MIN_HEIGHT = 550;

    /**
     * {@inheritDoc}
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
     * @param args unused
     * @throws IOException 
     */
    public static void main(final String[] args) throws IOException {
        initialization();
        launch();
    }

    /**
     * 
     * if not present, create the folder to keep the game files.
     */
    private static void initialization() throws IOException {

        if (new File(BrickBreakerEvo.MAIN_FOLDER).mkdirs()) {
            System.out.println("Main Folder successfully created");
        }
        if (new File(BrickBreakerEvo.LEVEL_FOLDER).mkdirs()) {
            System.out.println("Level Folder successfully created");
        }
        if (new File(BrickBreakerEvo.SETTINGS_FOLDER).mkdirs()
                && new File(BrickBreakerEvo.SETTINGS_FOLDER + "settings.json").createNewFile()) {
            System.out.println("Settings Folder e json successfully created");
        }
        if (new File(BrickBreakerEvo.MAPS_FOLDER).mkdirs()) {
            System.out.println("Maps Folder successfully created");
        }

        if (new File(BrickBreakerEvo.LEADERBOARDS_FOLDER).mkdirs()
                && new File(BrickBreakerEvo.LEADERBOARDS_FOLDER + "Ranking.json").createNewFile()) {
            System.out.println("LeaderBoards Folder e json successfully created");
        //new MainMenuView(TITLE).show();
        }
    }
}
