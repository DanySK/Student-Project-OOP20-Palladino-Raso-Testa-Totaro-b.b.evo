package view.tutorial;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.menu.SceneController;
import controller.menu.SceneControllerImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;
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


    private static final int SIZEFONTTITLE = 64;
    private static final int SIZEFONT = 24;
    private SceneController sceneController;
    private MediaPlayer player;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            this.loadVideo();
            this.loadFont();
            this.loadListener();
            this.resizable();
        } catch (NullPointerException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void loadVideo() throws NullPointerException, MalformedURLException {
        final URL videoUrl = new URL(new File(this.getClass().getResource(PersonalImages.TUTORIAL_VIDEO.getPath()).getFile()).toURI().toString());
        final Media media = new Media(videoUrl.toExternalForm());
        this.player = new MediaPlayer(media);
        player.setAutoPlay(true);

        // Add player at MediaView
        this.videoTutorial.setMediaPlayer(player);
    }

    private void loadFont() {
        this.lblTitle.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_TITLE.getPath()), SIZEFONTTITLE));
        this.buttonBack.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
    }

    private void loadListener() {
        // ButtonBack Listener
        this.buttonBack.setOnAction(event -> {
            this.sceneController = new SceneControllerImpl((Stage) ((Node) event.getSource()).getScene().getWindow());
            this.sceneController.switchScene(PersonalViews.SCENE_MAIN_MENU.getTitleScene(),
                                             PersonalViews.SCENE_MAIN_MENU.getPath(), 
                                             this.window.getWidth(),
                                             this.window.getHeight());
            player.stop();
        });
    }

    private void resizable() {

        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.videoTutorial.fitHeightProperty().bind(this.panel.heightProperty().divide(2));
        this.videoTutorial.fitWidthProperty().bind(this.panel.widthProperty().divide(2));

        this.buttonBack.prefWidthProperty().bind(this.containerBackButton.widthProperty().divide(3));
        this.buttonBack.prefHeightProperty().bind(this.containerBackButton.heightProperty().divide(4));
    }

}
