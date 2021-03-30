package view.game;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.utilities.PersonalFonts;
import view.utilities.PersonalImages;
import view.utilities.PersonalSounds;
import view.utilities.PersonalViews;

public class ControllerGame implements Initializable {
     /**
     *  Method that initialize all component of scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.loadFont();
        this.loadListener();
        this.resizable();
        this.loadImage();
    }

    private void loadImage() {
    }

    private void loadFont() {

    }

    private void loadListener() {
    }

    private void resizable() {
    }

}
