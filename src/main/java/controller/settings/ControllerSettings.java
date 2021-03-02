package controller.settings;



import java.net.URL;
import java.util.ResourceBundle;

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


public class ControllerSettings implements Initializable{
	
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resizable();
		this.loadFont();
		this.loadListener();
	}
	

	private void loadListener() {
		this.btnBack.setOnAction(event -> {
			this.sceneController = new SceneControllerImpl((Stage)((Node)event.getSource()).getScene().getWindow());
			this.sceneController.switchScene("BrickBreaker-EVO", "/Layout/MainMenu.fxml", this.window.getWidth(), this.window.getHeight());
		});
		
	}


	private void loadFont() {
		this.lblTitle.setFont(Font.loadFont(this.getClass().getResourceAsStream("/Font/BungeeShade-Regular.ttf"), 64));
		this.ckSoundFX.setFont(Font.loadFont(this.getClass().getResourceAsStream("/Font/Staatliches-Regular.ttf"), 24));
		this.ckSound.setFont(Font.loadFont(this.getClass().getResourceAsStream("/Font/Staatliches-Regular.ttf"), 24));
		this.rbUseLeftRight.setFont(Font.loadFont(this.getClass().getResourceAsStream("/Font/Staatliches-Regular.ttf"), 24));
		this.rbUseUpDown.setFont(Font.loadFont(this.getClass().getResourceAsStream("/Font/Staatliches-Regular.ttf"), 24));
		this.btnBack.setFont(Font.loadFont(this.getClass().getResourceAsStream("/Font/Staatliches-Regular.ttf"), 24));
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
