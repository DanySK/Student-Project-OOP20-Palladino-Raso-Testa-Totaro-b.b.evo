package controller.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneControllerImpl implements SceneController {

        private final Stage currentStage;
	
	public SceneControllerImpl(final Stage currentStage) {
		this.currentStage = currentStage;
	}

	@Override
	public void switchScene(final String title, final String url, final double width, final double height) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(url));

			Scene newScene = new Scene(parent, width, height);
			Image cursor = new Image(this.getClass().getResourceAsStream("/images/cursor/pacmanCursor.png"));
			newScene.setCursor(new ImageCursor(cursor));
			this.currentStage.setScene(newScene);
			this.currentStage.setTitle(title);
			this.currentStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
