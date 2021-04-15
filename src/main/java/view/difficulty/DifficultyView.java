package view.difficulty;

import java.net.URL;
import java.util.ResourceBundle;

import controller.game.GameLoop;
import controller.menu.SceneLoader;
import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.mapeditor.LevelSelection;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.settings.SettingLevelManager;
import model.utilities.Difficulty;
import model.utilities.GameUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.FXMLMenuController;

public class DifficultyView implements Initializable, FXMLMenuController {

    @FXML
    private AnchorPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private VBox radioButtonContainer;

    @FXML
    private RadioButton ckNormalDifficulty;

    @FXML
    private RadioButton ckHardDifficulty;

    @FXML
    private Button btnStartGame;

    @FXML
    private HBox btnBackContainer;

    @FXML
    private Button btnBack;

    private SettingsController controller;

    /**
     * Method that initialize all javaFx component.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.controller = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);
        this.resizable();
        this.loadFont();
        this.loadListener();
        this.loadAnimation();
        this.updateViewComponent();
    }

    private void updateViewComponent() {
        this.ckNormalDifficulty.setSelected(this.controller.getDifficulty().equals(Difficulty.NORMAL));
        this.ckHardDifficulty.setSelected(this.controller.getDifficulty().equals(Difficulty.HARD));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadListener() {
        //Button back Listener
        this.btnBack.setOnAction(event -> {
            this.switchPage(PersonalViews.SCENE_MAIN_MENU, this.window.getWidth(), this.window.getHeight(), PersonalStyle.DEFAULT_STYLE);
            this.controller.saveNewSettings();
            this.soundClick();
         });

        //Button StartGame Listener
        this.btnStartGame.setOnAction(event -> {
            //Don't permit to resize the next scene.
            final Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.setResizable(false);

            this.switchPage(PersonalViews.SCENE_GAME, GameUtilities.SCREEN_WIDTH, GameUtilities.SCREEN_HEIGHT, PersonalStyle.DEFAULT_STYLE);

            //Save the current game settings
            this.controller.saveNewSettings();

            //Play Button CLick Sound
            this.soundClick();

            //Used to stop game music
            this.stopCurrentGameMusic();

            final SettingLevelBuilder setBuilder = new SettingLevelBuilder();
            setBuilder.fromSettings(SettingLevelManager.loadOption());
            setBuilder.selectLevel(LevelSelection.LEVEL1.getLevel());
            SettingLevelManager.saveOption(setBuilder.build());
            //UserManager.saveUser(new User());

            final Scene scene = this.btnStartGame.getScene();
            final Thread thread = new Thread(new GameLoop(scene));
            thread.setDaemon(true);
            thread.start();
         });

        //RadioButton change difficulty
        this.ckNormalDifficulty.selectedProperty().addListener((obs, oldV, newV) -> {
            this.controller.changeDifficulty(Difficulty.NORMAL);
            this.soundClick();
        });

        this.ckHardDifficulty.selectedProperty().addListener((obs, oldV, newV) -> {
            this.controller.changeDifficulty(Difficulty.HARD);
            this.soundClick();
        });
    }

    /**
     * Used to stop the current game music.
     */
    private void stopCurrentGameMusic() {
        SoundController.stopMusic();
    }

    /**
     * Method that allow to play the button's sound.
     */
    private void soundClick() {
        SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
    }

    /**
     * This method allows to switch the current scene whit the next or previous scene.
     * @param scene - use to set the next or previous scene.
     * @param width - use to set the width for next scene.
     * @param height - use to set the height for next scene.
     * @param style - use to set the style for the next or previous scene.
     */
    private void switchPage(final PersonalViews scene, final double width, final double height, final PersonalStyle style) {
        //Switch Scene
        SceneLoader.switchScene((Stage) this.window.getScene().getWindow(), 
                                 scene.getURL(), 
                                 scene.getTitleScene(), 
                                 width, 
                                 height,
                                 style.getStylePath());
    }
    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadFont() {
        this.lblTitle
            .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnStartGame
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.ckHardDifficulty
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.ckNormalDifficulty
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.btnBack
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void resizable() {
        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.btnBack.prefWidthProperty().bind(this.btnBackContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnStartGame.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.ckNormalDifficulty.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.ckHardDifficulty.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));

        this.lblTitle.setWrapText(false);
    }
}
