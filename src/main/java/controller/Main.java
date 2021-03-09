package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


import controller.menu.SceneController;
import controller.menu.SceneControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import view.utilities.PersonalViews;

public class Main extends Application {

    private static final int MIN_WIDHT = 450;
    private static final int MIN_HEIGHT = 550;

    /**
     *
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        /*Get screen size */
        final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final SceneController sceneController = new SceneControllerImpl(primaryStage);

        sceneController.switchScene(PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                PersonalViews.SCENE_MAIN_MENU.getPath(), 
                graphicsDevice.getDisplayMode().getWidth() / 2, 
                graphicsDevice.getDisplayMode().getHeight() / 2);

        primaryStage.setMinWidth(MIN_WIDHT);
        primaryStage.setMinHeight(MIN_HEIGHT);
    }

    public static void main(final String[] args) {
        launch();
    }

}
