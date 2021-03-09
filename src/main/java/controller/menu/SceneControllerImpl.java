package controller.menu;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.utilities.PersonalImages;


public class SceneControllerImpl implements SceneController {

        private static final int CURSOR_DIMENSION = 30;
        private final Stage currentStage;

        public SceneControllerImpl(final Stage primaryStage) {
            this.currentStage = primaryStage;
        }

        /**
         * Method that allows to change the current scene having an stage.
         */
        @Override
        public void switchScene(final String title, final String url, final double width, final double height) {
            try {
                    final Parent parent = FXMLLoader.load(getClass().getResource(url));
                    final Scene newScene = new Scene(parent, width, height);
                    final Image cursor = new Image(this.getClass().getResourceAsStream(PersonalImages.CURSOR_PACMAN_IMG.getPath()));
                    newScene.setCursor(new ImageCursor(cursor, CURSOR_DIMENSION, CURSOR_DIMENSION));
                    this.currentStage.setScene(newScene);
                    this.currentStage.setTitle(title);
                    this.currentStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
}
