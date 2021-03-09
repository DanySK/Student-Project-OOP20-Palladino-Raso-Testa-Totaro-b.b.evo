package view.settings;



import java.net.URL;
import java.util.ResourceBundle;

import controller.menu.PersonalFonts;
import controller.menu.PersonalViews;
import controller.menu.SceneController;
import controller.menu.SceneControllerImpl;
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


        /**
         *
         */
        @Override
        public void initialize(final URL location, final ResourceBundle resources) {
                this.resizable();
                this.loadFont();
                this.loadListener();
        }

        private void loadListener() {
                this.btnBack.setOnAction(event -> {
                this.sceneController = new SceneControllerImpl((Stage) ((Node) event.getSource()).getScene().getWindow());
                this.sceneController.switchScene(PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                                 PersonalViews.SCENE_MAIN_MENU.getPath(), 
                                                 this.window.getWidth(), 
                                                 this.window.getHeight());
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

                this.ckSoundFX.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(3));
                this.ckSound.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(3));
                this.rbUseLeftRight.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(3));
                this.rbUseUpDown.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(3));
                this.btnBack.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(3));

                this.lblTitle.setWrapText(true);
        }
}
