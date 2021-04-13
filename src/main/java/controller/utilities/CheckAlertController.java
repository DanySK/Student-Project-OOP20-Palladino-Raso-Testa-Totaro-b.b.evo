package controller.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Used to check if the fields and the name level.
 * DA GUARDRE SE TENERLA QUI O SPOSTARLA
 *
 */
public class CheckAlertController {

    private Alert alert;

    /**
     * Check if all fields are entered correctly. 
     */
    public void checkAllField() {
        alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText("You must fill all fields!");
        alert.showAndWait();
    }

    /**
     * Check if the name entered is the same as a predefined level name.
     */
    public void checkLevelName() {
        alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText("The name you have selected already belongs to a predefined level and cannot be overwritten!");
        alert.showAndWait();
    }

    /**
     * Check if the level was created correctly.
     */
    public void checkLevelCreate() {
        alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("The level was successfully created");
        alert.showAndWait();
    }
}
