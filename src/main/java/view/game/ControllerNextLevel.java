package view.game;

import controller.game.GameLoop;
import controller.menu.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.leaderboard.Player;
import model.mapeditor.Level;
import model.utilities.GameUtilities;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.GUILayout;

/**
 * Controller of nextLevel.fxml.
 */
public class ControllerNextLevel implements GUILayout{

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
    @FXML
    public void initialize() {
        this.mainPanel.setMinWidth(GameUtilities.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(GameUtilities.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(GameUtilities.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(GameUtilities.SCREEN_HEIGHT);
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
