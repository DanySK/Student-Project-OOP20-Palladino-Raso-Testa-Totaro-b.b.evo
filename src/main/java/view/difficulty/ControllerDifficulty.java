package view.difficulty;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;

public class ControllerDifficulty implements Initializable {

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

    private static final int SIZEFONTTITLE = 64;
    private static final int SIZEFONT = 24;
    private static final int SIZEWIDTH = 20;
    private static final int SIZEHEIGHT = 20;
    private static final int CENTER_POSITION = 2;

    /**
     * Method that initialize all javaFx component.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadListener();
        this.loadAnimation();
        this.loadImage();
    }

    private void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    private void loadImage() {
        final ImageView imgBack = new ImageView(
                new Image(PersonalImages.BACK_IMG.getResourceAsStream()));
        imgBack.setFitWidth(SIZEWIDTH);
        imgBack.setFitHeight(SIZEHEIGHT);
        this.btnBack.setGraphic(imgBack);
    }

    private void loadListener() {

    }

    private void loadFont() {
        this.lblTitle
            .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), SIZEFONTTITLE));
        this.btnStartGame
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), SIZEFONT));
        this.ckHardDifficulty
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), SIZEFONT));
        this.ckNormalDifficulty
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), SIZEFONT));
        this.btnBack
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), SIZEFONT));
    }

    private void resizable() {
        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.btnBack.prefWidthProperty().bind(this.btnBackContainer.widthProperty().divide(CENTER_POSITION));
        this.btnStartGame.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));
        this.ckNormalDifficulty.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));
        this.ckHardDifficulty.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));

        this.lblTitle.setWrapText(false);
    }
}
