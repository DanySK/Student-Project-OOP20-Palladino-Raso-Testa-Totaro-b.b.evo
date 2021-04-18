package controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import controller.game.GameLoop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.leaderboard.Player;
import model.mapeditor.Level;
import model.mapeditor.LevelSelection;
import model.settings.SettingLevelManager;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalStyle;
import view.GUILayout;
import view.PersonalViews;
import view.SceneLoader;

/**
 * Controller of nextLevel.fxml.
 */
public class ControllerNextLevel implements Initializable, GUILayout {

    @FXML
    private SplitPane mainPanel;

    @FXML
    private Label lblLevel;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblScore;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnNext;

    /**
     * Initialize the window with default settings adapted to the monitor resolution.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.mainPanel.setMinWidth(ScreenUtilities.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenUtilities.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenUtilities.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenUtilities.SCREEN_HEIGHT);
    }

    /**
     * @param event when click the button menu the user return to the Main Menu
     */
    @FXML
    void backToMenu(final MouseEvent event) {
        SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                PersonalViews.SCENE_MAIN_MENU.getURL(), 
                PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                this.mainPanel.getWidth(), 
                this.mainPanel.getHeight(),
                PersonalStyle.DEFAULT_STYLE.getStylePath());
    }

    /**
     * Start a new gameLoop thread with the next level set before in the gameLoop.
     */
    @FXML
    public void goToNextLevel() {

        ///final SettingLevelBuilder setBuilder = new SettingLevelBuilder();
        //setBuilder.fromSettings(SettingLevelManager.loadOption());

        final Scene scene = btnNext.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true);
        engine.start();
    }

    /**
     * @param lvl the current level to display.
     * @param player to retrieve the information about score and lives.
     */
    public void update(final Level lvl, final Player player) {
        this.lblLevel.setText(lvl.getLevelName());
        this.lblLives.setText("YOUR LIVES: " + player.getLife());
        this.lblScore.setText("YOUR SCORE: " + player.getScore());
    }
}
