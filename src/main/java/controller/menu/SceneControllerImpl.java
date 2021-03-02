package controller.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneControllerImpl implements SceneController {

	private final Stage currentStage;
	
	public SceneControllerImpl(Stage currentStage) {
		this.currentStage = currentStage;
	}

	@Override
	public void switchScene(String title, String url,double width, double height) {
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource(url));
				
			Scene newScene = new Scene(parent,width,height);
			Image cursor = new Image(this.getClass().getResourceAsStream("/Immagini/cursor/pacmanCursor.png"));
			newScene.setCursor(new ImageCursor(cursor));
			this.currentStage.setScene(newScene);
			this.currentStage.setTitle(title);
			this.currentStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
