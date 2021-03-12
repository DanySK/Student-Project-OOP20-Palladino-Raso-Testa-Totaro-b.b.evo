package view.settings;



import java.net.URL;
import java.util.ResourceBundle;


import controller.menu.SceneController;
import controller.menu.SceneControllerImpl;
import controller.sound.SoundController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;
import view.utilities.PersonalSounds;
import view.utilities.PersonalViews;


public class ControllerSettings implements Initializable {

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
        private CheckBox ckSoundFX;

        @FXML
        private CheckBox ckSound;

        @FXML
        private RadioButton rbUseLeftRight;

        @FXML
        private RadioButton rbUseUpDown;

        @FXML
        private Button btnBack;

        private SceneController sceneController;
        private static final int SIZEFONTTITLE = 64;
        private static final int SIZEFONT = 24;
        private static final int SIZEWIDTH = 20;
        private static final int SIZEHEIGHT = 20;
        private static final int CENTER_POSITION = 3;


        /**
         *
         */
        @Override
        public void initialize(final URL location, final ResourceBundle resources) {
            this.resizable();
            this.loadFont();
            this.loadListener();
            this.loadImage();
        }
        private void loadImage() {
            final ImageView imgPlay = new ImageView(
                    new Image(this.getClass().getResourceAsStream(PersonalImages.BACK_IMG.getPath())));
            imgPlay.setFitWidth(SIZEWIDTH);
            imgPlay.setFitHeight(SIZEHEIGHT);
            this.btnBack.setGraphic(imgPlay);
        }
        private void loadListener() {

            //Button back Listener
            this.btnBack.setOnAction(event -> {
                this.sceneController = new SceneControllerImpl((Stage) ((Node) event.getSource()).getScene().getWindow());
                this.sceneController.switchScene(PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                                 PersonalViews.SCENE_MAIN_MENU.getPath(), 
                                                 this.window.getWidth(), 
                                                 this.window.getHeight());

                //Play Button CLick Sound
                SoundController.playSoundFx(this.getClass().getResource(PersonalSounds.TICK_BUTTON.getPath()).getPath());

             });

            //CheckBox SoundFx Listener
            this.ckSoundFX.selectedProperty().addListener((obs, oldV, newV) -> {

                //Play Sound
                SoundController.playSoundFx(this.getClass().getResource(PersonalSounds.TICK_SPECIALBUTTON.getPath()).getPath());
            });

            this.ckSound.selectedProperty().addListener((obs, oldV, newV) -> {

                //Play Sound
                SoundController.playSoundFx(this.getClass().getResource(PersonalSounds.TICK_SPECIALBUTTON.getPath()).getPath());
            });

            this.rbUseLeftRight.selectedProperty().addListener((obs, oldV, newV) -> {

                //Play Sound
                SoundController.playSoundFx(this.getClass().getResource(PersonalSounds.TICK_SPECIALBUTTON.getPath()).getPath());
            });

            this.rbUseUpDown.selectedProperty().addListener((obs, oldV, newV) -> {

                //Play Sound
                SoundController.playSoundFx(this.getClass().getResource(PersonalSounds.TICK_SPECIALBUTTON.getPath()).getPath());
            });
        }

        private void loadFont() {
                this.lblTitle.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_TITLE.getPath()), SIZEFONTTITLE));
                this.ckSoundFX.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
                this.ckSound.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
                this.rbUseLeftRight.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
                this.rbUseUpDown.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
                this.btnBack.setFont(Font.loadFont(this.getClass().getResourceAsStream(PersonalFonts.FONT_BUTTON.getPath()), SIZEFONT));
        }

        private void resizable() {

                this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                this.panel.prefHeightProperty().bind(this.window.heightProperty());
                this.panel.prefWidthProperty().bind(this.window.widthProperty());

                this.ckSoundFX.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));
                this.ckSound.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));
                this.rbUseLeftRight.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));
                this.rbUseUpDown.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));
                this.btnBack.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(CENTER_POSITION));

                this.lblTitle.setWrapText(true);
        }

}
