package view.menu;

import java.net.URL;
import java.util.ResourceBundle;

import controller.menu.SceneLoader;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.GameUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import view.FXMLMenuController;

public class MainMenuView implements Initializable, FXMLMenuController {

    @FXML
    private AnchorPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private HBox coinContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCoins;

    @FXML
    private VBox buttonContainer;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnCreativeMode;

    @FXML
    private Button btnTutorial;

    @FXML
    private Button btnRanking;

    /**
     * Initialize all view components.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadAnimation();
        this.loadListener();
        this.loadMusic();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadListener() {
        this.btnPlay.setOnAction(this.switchPage(PersonalViews.SCENE_CHARACTER_MENU, PersonalStyle.DEFAULT_STYLE));
        this.btnSettings.setOnAction(this.switchPage(PersonalViews.SCENE_SETTINGS, PersonalStyle.DEFAULT_STYLE));
        this.btnCreativeMode.setOnAction(this.switchPage(PersonalViews.SCENE_CREATIVEMODE, PersonalStyle.DEFAULT_STYLE));
        this.btnTutorial.setOnAction(this.switchPage(PersonalViews.SCENE_TUTORIAL, PersonalStyle.DEFAULT_STYLE));
        this.btnRanking.setOnAction(this.switchPage(PersonalViews.SCENE_RANKING, PersonalStyle.DEFAULT_STYLE));
    }

    /**
     * This method allows to switch the current scene whit the next scene.
     * @param scene - use to set the next scene.
     * @param style - use to set the style for the next scene.
     * @return an ActionEvent that allow to change between the current scene and the next scene.
     */
    private EventHandler<ActionEvent> switchPage(final PersonalViews scene, final PersonalStyle style) {
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                //Switch Scene
                SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                scene.getURL(), 
                scene.getTitleScene(), 
                window.getWidth(), 
                window.getHeight(),
                style.getStylePath());

                soundClick();
            }

        };
    }

    /**
     * Method that allow to play the button's sound.
     */
    private void soundClick() {
        SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadAnimation() {
        //Blink insert coin label
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblCoins.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblCoins.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
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
        this.btnPlay
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnSettings
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnCreativeMode
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnTutorial
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnRanking
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.lblCoins
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void resizable() {
        this.btnPlay.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnSettings.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnCreativeMode.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnTutorial.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnRanking.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.lblTitle.setWrapText(true);
    }

    /**
     * 
     * This method allow to start the game music.
     *
     */
    private void loadMusic() {
        //Play Button CLick Sound
        SoundController.playMusic(PersonalSounds.MAIN_THEME.getURL().getPath());
    }

}
