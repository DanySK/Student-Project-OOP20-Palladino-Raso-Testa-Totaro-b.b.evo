package view.character;

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
import javafx.scene.control.TextField;
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
import view.utilities.PersonalSounds;

public class ControllerCharacter implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private HBox buttonBackContainer;

    @FXML
    private VBox objectContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnBack;

    @FXML
    private TextField characterNameField;

    private static final int SIZEFONTTITLE = 48;
    private static final int SIZEFONT = 24;
    private static final int SIZEWIDTH = 20;
    private static final int SIZEHEIGHT = 20;
    private static final int CENTER_POSITION = 2;
    private static final int MAX_NAME_LENGHT = 12;

    /**
     *  Initialize all javaFx view components.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadListener();
        this.loadImage();
        this.loadAnimation();
    }

    private void loadImage() {
        final ImageView imgBack = new ImageView(
                new Image(this.getClass().getResourceAsStream(PersonalImages.BACK_IMG.getPath())));
        imgBack.setFitWidth(SIZEWIDTH);
        imgBack.setFitHeight(SIZEHEIGHT);
        this.btnBack.setGraphic(imgBack);

    }

    private void loadListener() {
        //TextField listener, not permission a long name control
        this.characterNameField.textProperty().addListener((ob, oldValue, newValue) -> {
            if (this.characterNameField.getText().length() > MAX_NAME_LENGHT) {
                final String name = characterNameField.getText().substring(0, MAX_NAME_LENGHT);
                this.characterNameField.setText(name);
            }
        });


    }

    private void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    private void loadFont() {
        this.lblTitle
            .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_TITLE.getPath()), SIZEFONTTITLE));
        this.btnBack
            .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        this.btnNext
            .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        this.characterNameField
        .setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_TITLE.getPath()), SIZEFONT));
    }

    private void resizable() {
        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.btnBack.prefWidthProperty().bind(this.buttonBackContainer.widthProperty().divide(CENTER_POSITION));
        this.btnNext.prefWidthProperty().bind(this.objectContainer.widthProperty().divide(CENTER_POSITION));
        this.characterNameField.setPrefWidth(10);

        this.lblTitle.setWrapText(true);

        //TextField
        this.characterNameField.setMinWidth(this.btnNext.getPrefWidth());
        this.characterNameField.setPrefWidth(this.btnNext.getPrefWidth());
        this.characterNameField.setFocusTraversable(false);


    }

}
