package view.tutorial;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.menu.SceneLoaderSingleton;
import controller.sound.SoundController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;
import view.utilities.PersonalSounds;
import view.utilities.PersonalViews;


public class ControllerTutorial implements Initializable {

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


    private static final int SIZEFONTTITLE = 42;
    private static final int SIZEFONT = 24;
    private static final int SIZEWIDTH = 20;
    private static final int SIZEHEIGHT = 20;
    private static final int CENTER_VIDEO_POSITION = 2;
    private static final int CENTER_BUTTON_POSITION = 4;
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
            this.loadImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void loadVideo() throws MalformedURLException {

        //Stop the menù music
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

    private void loadImage() {
        final ImageView imgPlay = new ImageView(
                new Image(PersonalImages.BACK_IMG.getResourceAsStream()));
        imgPlay.setFitWidth(SIZEWIDTH);
        imgPlay.setFitHeight(SIZEHEIGHT);
        this.buttonBack.setGraphic(imgPlay);
    }

    private void loadFont() {
        this.lblTitle.setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), SIZEFONTTITLE));
        this.buttonBack.setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), SIZEFONT));
    }

    private void loadListener() {
        // ButtonBack Listener
        this.buttonBack.setOnAction(event -> {
            SceneLoaderSingleton.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                    PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                    PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                    this.window.getWidth(), 
                                    this.window.getHeight());

            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());

            //Stop video
            player.stop();

        });

    }

    private void resizable() {

        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.videoTutorial.fitHeightProperty().bind(this.panel.heightProperty().divide(CENTER_VIDEO_POSITION));
        this.videoTutorial.fitWidthProperty().bind(this.panel.widthProperty().divide(CENTER_VIDEO_POSITION));

        this.buttonBack.prefWidthProperty().bind(this.containerBackButton.widthProperty().divide(CENTER_BUTTON_POSITION));
        this.buttonBack.prefHeightProperty().bind(this.containerBackButton.heightProperty().divide(CENTER_BUTTON_POSITION));

    }

}
