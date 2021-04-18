
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.GameUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalImages;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import resource.routing.TutorialImages;


public class ControllerTutorial implements Initializable, FXMLMenuController {

    @FXML
    private AnchorPane window;

    @FXML
    private HBox titleContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private VBox videoTutorialContainer;

    @FXML
    private ImageView videoTutorial;

    @FXML
    private VBox buttonsContainer;

    @FXML
    private Button buttonBack;

    @FXML
    private Button btnMenuTutorial;

    @FXML
    private Button btnHowToPlay;

    @FXML
    private Button btnSettingsTutorial;

     /**
     *  Method that initialize all component of scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            this.loadMedia();
            this.loadFont();
            this.loadListener();
            this.resizable();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Method used to load media into ImageView.
     *
     */
    private void loadMedia() throws MalformedURLException {
        //Load the animated image
        final URL videoUrl = new URL(new File(TutorialImages.TUTORIAL_DEFAULT.getURL().getFile()).toURI().toString());
        final Image image = new Image(videoUrl.toExternalForm());
        this.videoTutorial.setImage(image);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadFont() {
        this.lblTitle.setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.buttonBack.setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.btnHowToPlay.setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.btnMenuTutorial.setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.btnMenuTutorial.setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
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

            FXMLMenuController.switchScene((Stage) this.window.getScene().getWindow(), 
                                           PersonalViews.SCENE_MAIN_MENU, 
                                           PersonalStyle.DEFAULT_STYLE, 
                                           this.window.getScene().getWindow().getWidth(),
                                           this.window.getScene().getWindow().getHeight(),
                                           true);
        });

     // button HowToPlay Listener
        this.btnHowToPlay.setOnAction(event -> {
            this.lblTitle.setText("HOW TO PLAY");
            final Image i = new Image(new File(TutorialImages.TUTORIAL_HOW_TO_PLAY.getURL().getFile()).toURI().toString());
            this.videoTutorial.setImage(i);
        });
     // button MenuTutorial Listener
        this.btnMenuTutorial.setOnAction(event -> {
            this.lblTitle.setText("MENU TUTORIAL");
            final Image i = new Image(new File(TutorialImages.TUTORIAL_MAIN_MENU.getURL().getFile()).toURI().toString());
            this.videoTutorial.setImage(i);
        });
     // button SettingsTutorial Listener
        this.btnSettingsTutorial.setOnAction(event -> {
            this.lblTitle.setText("SETTINGS TUTORIAL");
            final Image i = new Image(new File(TutorialImages.TUTORIAL_SETTINGS.getURL().getFile()).toURI().toString());
            this.videoTutorial.setImage(i);
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
        
    }

}
