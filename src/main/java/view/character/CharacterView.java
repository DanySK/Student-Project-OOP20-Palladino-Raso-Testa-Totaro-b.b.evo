package view.character;

import java.net.URL;
import java.util.ResourceBundle;

import controller.menu.SceneLoader;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.GameUtilities;
import view.utilities.PersonalFonts;
import view.utilities.PersonalSounds;
import view.utilities.PersonalStyle;
import view.utilities.PersonalViews;

public class CharacterView implements Initializable {

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

    private static final String CLEAN_TEXT = "";

    /**
     *  Initialize all javaFx view components.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadListener();
        this.loadAnimation();
    }

    private void loadListener() {
        //TextField listener, not permission a long name control
        this.characterNameField.textProperty().addListener((ob, oldValue, newValue) -> {
            if (this.characterNameField.getText().length() > GameUtilities.MAX_ALIAS_LENGHT) {
                final String name = characterNameField.getText().substring(0, GameUtilities.MAX_ALIAS_LENGHT);
                this.characterNameField.setText(name);
            }
        });

        this.characterNameField.setOnMouseClicked(event -> {
            //Clear prompt text when user click
            this.characterNameField.setPromptText(CLEAN_TEXT);
        });

        //Button next Listener
        this.btnNext.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                     PersonalViews.SCENE_DIFFICULTY.getURL(), 
                                     PersonalViews.SCENE_DIFFICULTY.getTitleScene(), 
                                     this.window.getWidth(), 
                                     this.window.getHeight(),
                                     PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        });

        //Button back Listener
        this.btnBack.setOnAction(event -> {
            SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                    PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                    PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                    this.window.getWidth(), 
                                    this.window.getHeight(),
                                    PersonalStyle.DEFAULT_STYLE.getStylePath());

            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());

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
            .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnBack
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.btnNext
            .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        this.characterNameField
            .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
    }

    private void resizable() {
        this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.panel.prefHeightProperty().bind(this.window.heightProperty());
        this.panel.prefWidthProperty().bind(this.window.widthProperty());

        this.btnBack.prefWidthProperty().bind(this.buttonBackContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.btnNext.prefWidthProperty().bind(this.objectContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
        this.characterNameField.setPrefWidth(10);

        this.lblTitle.setWrapText(true);

        //TextField
        this.characterNameField.setMinWidth(this.btnNext.getPrefWidth());
        this.characterNameField.setPrefWidth(this.btnNext.getPrefWidth());
        this.characterNameField.setFocusTraversable(false);


    }

}
