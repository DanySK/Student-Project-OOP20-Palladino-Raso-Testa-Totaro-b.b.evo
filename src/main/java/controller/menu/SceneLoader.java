package controller.menu;

import java.io.IOException;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.utilities.PersonalImages;

public final class SceneLoader {

    private static final int CURSOR_DIMENSION = 30;
    private static final int ANIMATION_DURATION = 600;

    private SceneLoader() {

    }

    /**
     * @param stage - the current stage that use to switch scene
     * @param path - the path where the scene content
     * @param title - the title of the stage
     * @param width - the width of the stage 
     * @param height - the height of the stage
     * @param cssStylePath - the css applied to the current scene
     */
    public static void switchScene(final Stage stage, final URL path, final String title, 
                                    final double width, final double height, final String cssStylePath)  {
        try {
            final Parent parent = FXMLLoader.load(path);
            final Scene newScene = new Scene(parent, width, height);

            // Load cursor Image
            final Image cursor = new Image(PersonalImages.CURSOR_PACMAN_IMG.getResourceAsStream());
            newScene.setCursor(new ImageCursor(cursor, CURSOR_DIMENSION, CURSOR_DIMENSION));

            //Apply Style
            parent.getStylesheets().add(cssStylePath);

            //Animation
            final FadeTransition fadeIn = new FadeTransition(Duration.millis(ANIMATION_DURATION), newScene.getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

            //Load Stage Property
            stage.getIcons().add(new Image(PersonalImages.GAME_ICON_IMG.getResourceAsStream()));
            stage.setScene(newScene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
