package view.character;

import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.menu.SceneLoader;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.leaderboard.PlayerImpl;
import model.leaderboard.StandardScoreSortingStrategy;
import model.utilities.GameUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;

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
    private static final String DEFAULT_ALERT_TITLE = "Overwrite player";
    private static final String DEFAULT_ALERT_CONTENT = "The name is already used, do you want overwrite it?";
    private static final int INITIAL_VALUE = 0;
    private final LeaderboardController controller = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);

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
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        });

        //Button next Listener
        this.btnNext.setOnAction(event -> {
            //Control if the alias as present
            if (this.controller.viewLeaderboard().containsKey(this.characterNameField.getText().toUpperCase(Locale.ENGLISH))) {
                this.showAlertDialog();
            } else {
                this.saveTemporaryPlayer(this.characterNameField.getText());
                SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                        PersonalViews.SCENE_DIFFICULTY.getURL(), 
                        PersonalViews.SCENE_DIFFICULTY.getTitleScene(), 
                        this.window.getWidth(), 
                        this.window.getHeight(),
                        PersonalStyle.DEFAULT_STYLE.getStylePath());
            }

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

    private void saveTemporaryPlayer(final String alias) {
        this.controller.addPlayerInLeaderBoard(new PlayerImpl(alias.toUpperCase(Locale.ENGLISH), 
                                                                                INITIAL_VALUE, INITIAL_VALUE, INITIAL_VALUE));
        this.controller.saveSortLeaderboard(new StandardScoreSortingStrategy());
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

    private void showAlertDialog() {
        //Create alert
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(DEFAULT_ALERT_TITLE);
        alert.setHeaderText(DEFAULT_ALERT_CONTENT);
        alert.setContentText(DEFAULT_ALERT_CONTENT);

        //Create Button in alert
        final ButtonType yesButton = new ButtonType("Yes");
        final ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        //Control the choose
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            this.saveTemporaryPlayer(this.characterNameField.getText());
            SceneLoader.switchScene((Stage) this.window.getScene().getWindow(), 
                    PersonalViews.SCENE_DIFFICULTY.getURL(), 
                    PersonalViews.SCENE_DIFFICULTY.getTitleScene(), 
                    this.window.getWidth(), 
                    this.window.getHeight(),
                    PersonalStyle.DEFAULT_STYLE.getStylePath());
            //Play Button CLick Sound
            SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
        } else {
            alert.close();
        }
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
