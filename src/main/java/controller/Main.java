package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import controller.menu.SceneController;
import controller.menu.SceneControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int MIN_WIDHT = 450;
	private static final int MIN_HEIGHT = 550;
	private SceneController sceneController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*Get screen size */
		GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		this.sceneController = new SceneControllerImpl(primaryStage);
	        this.sceneController.switchScene("BrickBreaker-Evolution", "/Layout/MainMenu.fxml",graphicsDevice.getDisplayMode().getWidth() / 2,graphicsDevice.getDisplayMode().getHeight() / 2);
       
		primaryStage.setMinWidth(MIN_WIDHT);
                primaryStage.setMinHeight(MIN_HEIGHT);

		
	}
	
		
	public static void main(String[] args) {
		launch();
	}

}
