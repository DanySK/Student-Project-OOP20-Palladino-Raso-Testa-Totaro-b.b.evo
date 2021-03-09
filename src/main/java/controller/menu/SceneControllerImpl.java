package controller.menu;

import java.io.IOException;

import com.sun.glass.ui.Cursor;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class SceneControllerImpl implements SceneController {

    private final Stage currentStage;

    private final int cursorDimesion = 30;
	
    public SceneControllerImpl(final Stage currentStage) {
        this.currentStage = currentStage;
    }

    /**
     * Method that allows to change the current scene having an stage.
     */
    @Override
    public void switchScene(final String title, final String url, final double width, final double height) {
        try {
            final Parent parent = FXMLLoader.load(getClass().getResource(url));
            final Scene newScene = new Scene(parent, width, height);
            final Image cursor = new Image(this.getClass().getResourceAsStream("/Images/Cursor/PacmanCursor.png"));
            newScene.setCursor(new ImageCursor(cursor, this.cursorDimesion, this.cursorDimesion));
            this.currentStage.setScene(newScene);
            this.currentStage.setTitle(title);
            this.currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                }
        }
}
