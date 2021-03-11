package controller.menu;

import java.io.IOException;

<<<<<<< HEAD
import com.sun.glass.ui.Cursor;

=======
import javafx.animation.FadeTransition;
>>>>>>> fff650ef84f6c038091df35ca720879bd03e71f4
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.utilities.PersonalImages;


public class SceneControllerImpl implements SceneController {

<<<<<<< HEAD
    private final Stage currentStage;
    private static final int CURSOR_DIMENSION = 30;
	
    public SceneControllerImpl(final Stage currentStage) {
        this.currentStage = currentStage;
    }
=======
        private static final int CURSOR_DIMENSION = 30;
        private static final int ANIMATION_DURATION = 500;
        private final Stage currentStage;

        public SceneControllerImpl(final Stage primaryStage) {
            this.currentStage = primaryStage;
        }
>>>>>>> fff650ef84f6c038091df35ca720879bd03e71f4

<<<<<<< HEAD
    /**
     * Method that allows to change the current scene having an stage.
     */
    @Override
    public void switchScene(final String title, final String url, final double width, final double height) {
        try {
            final Parent parent = FXMLLoader.load(getClass().getResource(url));
            final Scene newScene = new Scene(parent, width, height);
            final Image cursor = new Image(this.getClass().getResourceAsStream("/Images/Cursor/PacmanCursor.png"));
            newScene.setCursor(new ImageCursor(cursor, this.CURSOR_DIMENSION, this.CURSOR_DIMENSION));
            this.currentStage.setScene(newScene);
            this.currentStage.setTitle(title);
            this.currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
=======
        /**
         * Method that allows to change the current scene having an stage.
         */
        @Override
        public void switchScene(final String title, final String url, final double width, final double height) {
            try {
                    final Parent parent = FXMLLoader.load(getClass().getResource(url));
                    final Scene newScene = new Scene(parent, width, height);

                    // Load cursor Image
                    final Image cursor = new Image(this.getClass().getResourceAsStream(PersonalImages.CURSOR_PACMAN_IMG.getPath()));
                    newScene.setCursor(new ImageCursor(cursor, CURSOR_DIMENSION, CURSOR_DIMENSION));

                    final FadeTransition fadeIn = new FadeTransition(Duration.millis(ANIMATION_DURATION), newScene.getRoot());
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                    //Load Stage Property
                    this.currentStage.getIcons().add(new Image(this.getClass().getResourceAsStream(PersonalImages.GAME_ICON_IMG.getPath())));
                    this.currentStage.setScene(newScene);
                    this.currentStage.setTitle(title);
                    this.currentStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
>>>>>>> 48916dcf62748c9731e280db536b2a77277f8e15
}
