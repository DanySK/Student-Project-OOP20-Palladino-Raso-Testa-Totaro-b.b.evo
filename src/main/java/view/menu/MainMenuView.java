package view.menu;

import java.net.URL;
import java.util.ResourceBundle;

import controller.menu.SceneLoader;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

public class MainMenuView implements Initializable {

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
    private Button btnTutorial;

    @FXML
    private Button btnRanking;

    /**
     * Initialize all javafx view components.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadAnimation();
        this.loadListener();
        this.loadMusic();
    }

    private void loadListener() {
        //Play Listener
        this.btnPlay.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                     PersonalViews.SCENE_CHARACTER_MENU.getURL(), 
                                     PersonalViews.SCENE_CHARACTER_MENU.getTitleScene(), 
                                     this.window.getWidth(), 
                                     this.window.getHeight(),
                                     PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        });

        // Settings Listener
        this.btnSettings.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                     PersonalViews.SCENE_SETTINGS.getURL(), 
                                     PersonalViews.SCENE_SETTINGS.getTitleScene(), 
                                     this.window.getWidth(), 
                                     this.window.getHeight(),
                                     PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        });

        // Tutorial Listener
        this.btnTutorial.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                    PersonalViews.SCENE_TUTORIAL.getURL(), 
                                    PersonalViews.SCENE_TUTORIAL.getTitleScene(), 
                                    this.window.getWidth(), 
                                    this.window.getHeight(),
                                    PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        });

        // Ranking Listener
        this.btnRanking.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                    PersonalViews.SCENE_RANKING.getURL(), 
                                    PersonalViews.SCENE_RANKING.getTitleScene(), 
                                    this.window.getWidth(), 
                                    this.window.getHeight(),
                                    PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        });
    }

    private void loadAnimation() {
        //Blink insert coin label
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblCoins.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblCoins.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    private void loadFont() {
        this.lblTitle
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnPlay
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnSettings
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnTutorial
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnRanking
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.lblCoins
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
    }

    private void resizable() {

        this.btnPlay.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnSettings.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnTutorial.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnRanking.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));

        // Title
        this.lblTitle.setWrapText(true);
    }

    private void loadMusic() {
        //Play Button CLick Sound
        SoundController.playMusic(PersonalSounds.MAIN_THEME.getURL().getPath());
    }
}
