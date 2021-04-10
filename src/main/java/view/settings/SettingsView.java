package view.settings;




import java.net.URL;
import java.util.ResourceBundle;


import controller.menu.SceneLoader;
import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.utilities.GameUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;


public class SettingsView implements Initializable {

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

        private SettingsController controller;


        /**
         * Method that initialize all javaFx component.
         */
        @Override
        public void initialize(final URL location, final ResourceBundle resources) {
            this.controller = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);
            this.resizable();
            this.loadFont();
            this.loadListener();
            this.updateViewComponent(this.controller.isSoundFxEnable(),
                                     this.controller.isMusicEnable(),
                                     this.controller.isLeftAndRightEnable(),
                                     this.controller.isUpAndDownEnable());
        }

        /**
         * Method used to update view component.
         * @param isSoundsFxEnable
         * @param isMusicEnable
         * @param isLeftAndRightEnable
         * @param isUpAndDownEnable
         */
        public void updateViewComponent(final boolean isSoundsFxEnable,
                                        final boolean isMusicEnable,
                                        final boolean isLeftAndRightEnable,
                                        final boolean isUpAndDownEnable) {
            this.ckSoundFX.setSelected(isSoundsFxEnable);
            this.ckSound.setSelected(isMusicEnable);
            this.rbUseLeftRight.setSelected(isLeftAndRightEnable);
            this.rbUseUpDown.setSelected(isUpAndDownEnable);
        }

        private void loadListener() {

            //Button back Listener
            this.btnBack.setOnAction(event -> {
                SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                                        PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                        PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                        this.window.getWidth(), 
                                        this.window.getHeight(),
                                        PersonalStyle.DEFAULT_STYLE.getStylePath());
                this.controller.saveNewSettings();
                //Play Button CLick Sound
                SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL().getPath());
             });

            //CheckBox SoundFx Listener
            this.ckSoundFX.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.changeSoundFxState(newV);
                //Play Sound
                SoundController.playSoundFx(PersonalSounds.TICK_SPECIALBUTTON.getURL().getPath());
            });

            this.ckSound.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.changeMusicState(newV);
                //Play Sound
                SoundController.playSoundFx(PersonalSounds.TICK_SPECIALBUTTON.getURL().getPath());
            });

            this.rbUseLeftRight.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.useLeftAndRightCommand();
                //Play Sound
                SoundController.playSoundFx(PersonalSounds.TICK_SPECIALBUTTON.getURL().getPath());
            });

            this.rbUseUpDown.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.useUpAndDownCommand();
                //Play Sound
                SoundController.playSoundFx(PersonalSounds.TICK_SPECIALBUTTON.getURL().getPath());
            });
        }

        private void loadFont() {
                this.lblTitle
                    .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), GameUtilities.FONT_NORMAL_LABEL_SIZE));
                this.ckSoundFX
                    .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
                this.ckSound
                    .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
                this.rbUseLeftRight
                    .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
                this.rbUseUpDown
                    .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
                this.btnBack
                    .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), GameUtilities.FONT_SUB_LABEL_SIZE));
        }

        private void resizable() {

                this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                this.panel.prefHeightProperty().bind(this.window.heightProperty());
                this.panel.prefWidthProperty().bind(this.window.widthProperty());


                this.ckSoundFX.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
                this.ckSound.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
                this.rbUseLeftRight.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
                this.rbUseUpDown.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));
                this.btnBack.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(GameUtilities.CENTER_DIVIDER));

                this.lblTitle.setWrapText(true);
        }

}
