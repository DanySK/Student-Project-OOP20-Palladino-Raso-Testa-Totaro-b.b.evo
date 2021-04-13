package controller.mapeditor;

import controller.utilities.GUIController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import model.mapeditor.LevelBuilder;

public class MapBuilderController implements GUIController {

    private GraphicsContext graphicsContex;
    private LevelBuilder levelBuilder;
    private final CheckAlertController alert = new CheckAlertController();

    /**
     * Initialize the level customization and set the mouse listeners on the canvas.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Draw a grid on canvas.
     */
    private void setCanvas() {

    }

    /**
     * Return to menu.
     */
    @FXML
    public void backToMenu() {

    }

    /**
     * Check if the forms have been filled correctly.
     * Call the level builder to create a level with elem.
     */
    @FXML
    public void buildLvl() {

        alert.checkAllField();
        alert.checkLevelName();

        alert.checkLevelCreate();

    }

    /**
     * Clear all the elements on the grid.
     */
    @FXML
    public void clearAll() {
        levelBuilder.deleteAll();
        this.setCanvas();
    }
}
