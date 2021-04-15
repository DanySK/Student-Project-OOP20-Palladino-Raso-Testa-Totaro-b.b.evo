package model.utilities;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Contains global information for setup the view adaptable to the resolution.
 */
public final class GameUtilities {

    /**
     * @return current screen size
     */
    private static final Rectangle2D SCREEN_RES = Screen.getPrimary().getBounds();

    /**
     * Width of the gui (portion adapted to the resolution).
     */
    public static final double SCREEN_WIDTH = SCREEN_RES.getWidth() / 1.2; 

    /**
     * Height of the gui (portion adapted to the resolution).
     */
    public static final double SCREEN_HEIGHT = SCREEN_RES.getHeight() / 1.2;

    /**
     * System Separator.
     */
    private static final String SEP = System.getProperty("file.separator");

    /**
     * System User Home Path.
     */
    private static final String RES_PATH = System.getProperty("user.home");

    /**
     * Use to have static font size label for all view.
     */
    public static final int FONT_NORMAL_LABEL_SIZE = 42;

    /**
     * Use to have static font size little label for all view.
     */
    public static final int FONT_SUB_LABEL_SIZE = 24;

    /**
     * This number represent the divider number to center object in scene.
     */
    public static final int CENTER_DIVIDER = 2;

    /**
     * This number represent the cursor dimension.
     */
    public static final int CURSOR_DIMENSION = 50;

    /**
     * This number represent the max length for alias name.
     */
    public static final int MAX_ALIAS_LENGHT = 12;

    /**
     * This number represent the animation transition duration.
     */
    public static final int ANIMATION_DURATION = 600;
    /**
     * Path for the leaderboard file.
     */
    public static final String LEADERBOARD_PATH = RES_PATH
                                                   + SEP
                                                   + ".BrickBreakerEvo" 
                                                   + SEP
                                                   + "Leaderboards"
                                                   + SEP
                                                   + "ranking.json"; 
    /**
     * Path for the settings file.
     */
    public static final String SETTINGS_PATH =  RES_PATH
                                                + SEP
                                                + ".BrickBreakerEvo" 
                                                + SEP
                                                + "Settings"
                                                + SEP
                                                + "settings.json";

    /**
     * Used when player don't set the alias.
     */
    public static final String DEFAULT_PLAYER_NAME = "GUEST";

    /**
     * Path for save level setting.
     */
    public static final String SETTINGS_LEVEL_PATH =  RES_PATH
                                                + SEP
                                                + ".BrickBreakerEvo" 
                                                + SEP
                                                + "Settings"
                                                + SEP
                                                + "settinglevel.json"; 

    /**
     * The minimum WIDTH number that the stage can have.
     */
    public static final int MIN_RESIZE_WIDTH = 460;

    /**
     * The minimum HEIGHT number that the stage can have.
     */
    public static final int MIN_RESIZE_HEIGHT = 650;
    /**
     * Width of the game world (used in model World).
     */
    public static final double WORLD_WIDTH = 611;

    /**
     * Height of the game world (used in model World).
     */
    public static final double WORLD_HEIGHT = 611;

    /**
     * Width of the canvas in GameController gui (adapted to the height res for make a square).
     */
    public static final double CANVAS_WIDTH = SCREEN_HEIGHT / 1.3;

    /**
     * Height of the canvas in GameController gui (adapted to the height res for make a square).
     */
    public static final double CANVAS_HEIGHT = SCREEN_HEIGHT / 1.0;

    /**
     * Convert every entity of the game width (adapted to the current resolution).
     */
    public static final double REAL_X = CANVAS_WIDTH / WORLD_WIDTH;

    /**
     * Convert every entity of the game height (adapted to the current resolution).
     */
    public static final double REAL_Y = CANVAS_HEIGHT / WORLD_HEIGHT;

    /**
     * the number of bricks on the x-axis.
     */
    public static final double BRICK_NUMBER_X = 13;

    /**
     * the number of bricks on the y-axis.
     */
    public static final double BRICK_NUMBER_Y = 26;



    private GameUtilities() {

    }
}
