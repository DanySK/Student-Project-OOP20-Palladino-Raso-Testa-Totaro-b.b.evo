package view.menu;

import java.net.URL;
import java.util.ResourceBundle;


import controller.menu.SceneController;
import controller.menu.SceneControllerImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;
import view.utilities.PersonalViews;

public class ControllerMainMenu implements Initializable {

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

    private SceneController sceneController;
    private static final int SIZEFONT = 42;
    private static final int SIZEFONTCOIN = 24;
    private static final int SIZEWIDTH = 40;
    private static final int SIZEHEIGHT = 40;


    /**
     *
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadButtonImage();
        this.loadAnimation();
        this.loadListener();
    }

    private void loadListener() {

        // Settings Listener
        this.btnSettings.setOnAction(event -> {
            this.sceneController = new SceneControllerImpl((Stage) ((Node) event.getSource()).getScene().getWindow());
            this.sceneController.switchScene(PersonalViews.SCENE_SETTINGS.getTitleScene(),
                                             PersonalViews.SCENE_SETTINGS.getPath(), 
                                             this.window.getWidth(),
                                             this.window.getHeight());
        });

        // Tutorial Listener
        this.btnTutorial.setOnAction(event -> {
            this.sceneController = new SceneControllerImpl((Stage) ((Node) event.getSource()).getScene().getWindow());
            this.sceneController.switchScene(PersonalViews.SCENE_TUTORIAL.getTitleScene(), 
                                             PersonalViews.SCENE_TUTORIAL.getPath(), 
                                             this.window.getWidth(),
                                             this.window.getHeight()); 
        });

        // Ranking Listener
        this.btnRanking.setOnAction(event -> {
            this.sceneController = new SceneControllerImpl((Stage) ((Node) event.getSource()).getScene().getWindow());
            this.sceneController.switchScene(PersonalViews.SCENE_RANKING.getTitleScene(), 
                                             PersonalViews.SCENE_RANKING.getPath(), 
                                             this.window.getWidth(),
                                             this.window.getHeight()); 
        });
    }

    private void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblCoins.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblCoins.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    private void loadFont() {
        this.lblTitle
                .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_TITLE.getPath()), SIZEFONT));
        this.btnPlay
                .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        this.btnSettings
                .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        this.btnTutorial
                .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        this.btnRanking
                .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        this.lblCoins.setFont(
                Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONTCOIN));
    }

    private void loadButtonImage() {

        // ButtonPlay
        final ImageView imgPlay = new ImageView(
                new Image(this.getClass().getResourceAsStream(PersonalImages.PLAY_IMG.getPath())));
        imgPlay.setFitWidth(SIZEWIDTH);
        imgPlay.setFitHeight(SIZEHEIGHT);
        this.btnPlay.setGraphic(imgPlay);

        // ButtonSettings
        final ImageView imgSettings = new ImageView(
                new Image(this.getClass().getResourceAsStream(PersonalImages.SETTINGS_IMG.getPath())));
        imgSettings.setFitWidth(SIZEWIDTH);
        imgSettings.setFitHeight(SIZEHEIGHT);
        this.btnSettings.setGraphic(imgSettings);

        // ButtonTutorial
        final ImageView imgTutorial = new ImageView(
                new Image(this.getClass().getResourceAsStream(PersonalImages.TUTORIAL_IMG.getPath())));
        imgTutorial.setFitWidth(SIZEWIDTH);
        imgTutorial.setFitHeight(SIZEHEIGHT);
        this.btnTutorial.setGraphic(imgTutorial);

        // ButtonRanking
        final ImageView imgRanking = new ImageView(
                new Image(this.getClass().getResourceAsStream(PersonalImages.RANKING_IMG.getPath())));
        imgRanking.setFitWidth(SIZEWIDTH);
        imgRanking.setFitHeight(SIZEHEIGHT);
        this.btnRanking.setGraphic(imgRanking);
    }

    private void resizable() {

        this.btnPlay.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(2));
        this.btnSettings.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(2));
        this.btnTutorial.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(2));
        this.btnRanking.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(2));

        // Title
        this.lblTitle.setWrapText(true);
    }

}
