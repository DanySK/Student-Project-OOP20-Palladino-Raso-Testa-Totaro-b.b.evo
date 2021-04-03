package controller.mapeditor;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Used to check if the fileds and the name level.
 * DA GUARDRE SE TENERLA QUI O SPOSTARLA
 *
 */
public class CheckAlertController {

    private final Alert alert = new Alert(AlertType.WARNING);

    /**
     * Check if all fields are entered correctly. 
     */
    public void checkAllField() {
        alert.setHeaderText("Warning");
        alert.setContentText("You must fill all fields!");
        alert.showAndWait();
    }

    /**
     * Check if the name entered is the same as a predefined level name.
     */
    public void checkLevelName() {
        alert.setHeaderText("Warning");
        alert.setContentText("The name you have selected already belongs to a predefined level and cannot be overwritten!");
        alert.showAndWait();
    }
}
