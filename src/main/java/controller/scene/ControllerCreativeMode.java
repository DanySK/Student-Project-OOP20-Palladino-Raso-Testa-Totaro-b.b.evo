package controller.scene;

import controller.game.GameStateImpl;
import controller.utilities.CheckAlertController;
import controller.utilities.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.mapeditor.Level;
import model.mapeditor.LevelManager;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.settings.SettingLevelManager;
import model.utilities.GameUtilities;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;

public class ControllerCreativeMode implements GUIController {

    private static final double EDITOR_MODE_WIDTH = 800;

    private static final double EDITOR_MODE_HEIGHT = 500;

    private Level currentLevel;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button menuBtn;

    @FXML
    private Button builderBtn;

    @FXML
    private Button playBtn;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox levelContainer;

    @FXML
    private Label levelSelected;

    /**
     * initializes the window by setting dimensions.
     */
    @FXML
    public void initialize() {
        this.loadListener();
        this.update();
    }

    /**
     * implementation of pattern observer.
     * reads the new generated levels and creates a button as a reference
     * by copying the style from an existing one
     * {@inheritDoc}
     */
    public void update() {
        this.levelContainer.getChildren().clear();
        for (final Level lvl : LevelManager.loadLevels()) {
            final Button b = new Button(lvl.getLevelName());
            this.setButtonStyle(b, menuBtn);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    levelSelected.setOpacity(100);
                    currentLevel = lvl;
                    levelSelected.setText("Level name : " + lvl.getLevelName() + "\n"
                                          + "Background : " + lvl.getBackground().getTheme() + "\n"
                                          + "Music : " + lvl.getMusic().getName() + "\n"
                                          + "PaddleTexture : " + lvl.getPaddleTexture() + "\n"
                                          + "BallTexture : " + lvl.getBallTexture());
                }
            });
            this.levelContainer.getChildren().add(b);
        }
    }

    /**
     * 
     */
    @FXML
    private void loadListener() {
        //MenuButton return to menu
        this.menuBtn.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.panel.getScene().getWindow(), PersonalViews.SCENE_MAIN_MENU, PersonalStyle.DEFAULT_STYLE, 
                GameUtilities.SCREEN_WIDTH, GameUtilities.SCREEN_HEIGHT, false));
        //BuilderButton go to LevelBuilder
        this.builderBtn.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.panel.getScene().getWindow(), PersonalViews.SCENE_EDITOR_MODE, PersonalStyle.DEFAULT_STYLE, 
                EDITOR_MODE_WIDTH, EDITOR_MODE_HEIGHT, true));
    }

    /**
     * loads the currently selected level and starts the gameloop cycle.
     */
    @FXML
    private void playLevel() {
        if (!levelSelected.getText().isBlank()) {
            GameStateImpl.setCreativeMode(true);
            final SettingLevelBuilder levelBuilder = new SettingLevelBuilder();
            levelBuilder.fromSettings(SettingLevelManager.loadOption());
            levelBuilder.selectLevel(currentLevel);
            SettingLevelManager.saveOption(levelBuilder.build());

            this.playBtn.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.panel.getScene().getWindow(), PersonalViews.SCENE_CHARACTER_MENU, PersonalStyle.DEFAULT_STYLE, 
                    GameUtilities.SCREEN_WIDTH, GameUtilities.SCREEN_HEIGHT, false));

        } else {
            CheckAlertController.checkLevelSelected();
        }
    }

    /**
     * copy the style of a button to another button.
     * @param subject the button that takes the new style
     * @param reference the button that gives the new style
     */
    private void setButtonStyle(final Button subject, final Button reference) {
        subject.setStyle(reference.getStyle());
        subject.setEffect(reference.getEffect());
        subject.setFont(reference.getFont());
    }
}
