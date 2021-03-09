package view.tutorial;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

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

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

}
