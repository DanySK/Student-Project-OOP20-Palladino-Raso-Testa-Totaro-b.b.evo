package controller.scene;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalImages;
import resource.routing.PersonalStyle;
import view.PersonalViews;


public class ControllerTutorial implements Initializable, FXMLMenuController {

    @FXML
    private AnchorPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private VBox videoTutorialContainer;

    @FXML
    private MediaView videoTutorial;

    @FXML
    private HBox containerBackButton;

    @FXML
    private Button buttonBack;

    private MediaPlayer player;

     /**
     *  Method that initialize all component of scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            this.loadVideo();
            this.loadFont();
            this.loadListener();
            this.resizable();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Method used to load video into Mediaplayer.
     *
     */
    private void loadVideo() throws MalformedURLException {

        //Stop the menu music
        SoundController.stopMusic();

        //Load the video
        final URL videoUrl = new URL(new File(PersonalImages.TUTORIAL_VIDEO.getURL().getFile()).toURI().toString());
        final Media media = new Media(videoUrl.toExternalForm());

        //Set video into player
        this.player = new MediaPlayer(media);
        this.player.setAutoPlay(true);
        this.player.seek(Duration.INDEFINITE);

        // Add player at MediaView
        this.videoTutorial.setMediaPlayer(player);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadFont() {
        this.lblTitle.setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.buttonBack.setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadListener() {
        // ButtonBack Listener
        this.buttonBack.setOnAction(event -> {

            //Stop video
            player.stop();

            FXMLMenuController.switchScene((Stage) this.window.getScene().getWindow(), 
                                           PersonalViews.SCENE_MAIN_MENU, 
                                           PersonalStyle.DEFAULT_STYLE, 
                                           this.window.getScene().getWindow().getWidth(),
                                           this.window.getScene().getWindow().getHeight(),
                                           true);
        });

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
    public void resizable() {

        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.videoTutorial.fitHeightProperty().bind(this.panel.heightProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.videoTutorial.fitWidthProperty().bind(this.panel.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));

        this.buttonBack.prefWidthProperty().bind(this.containerBackButton.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.buttonBack.prefHeightProperty().bind(this.containerBackButton.heightProperty().divide(ScreenUtilities.CENTER_DIVIDER));

    }

}
