package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;

public interface FXMLMenuController {

    /**
     * This method allows to set all listeners for the view components.
     */
    void loadListener();

    /**
     * This method allows to set the fonts for the view components.
     */
    void loadFont();

    /**
     * This method allows to set the animations for the view components.
     */
    void loadAnimation();

    /**
     * This method allows to switch the current scene whit the next scene.
     * @param scene - use to set the next scene.
     * @param style - use to set the style for the next scene.
     * @return an ActionEvent that allow to change between the current scene and the next scene.
     */
    EventHandler<ActionEvent> switchPage(PersonalViews scene, PersonalStyle style);

    /**
     *  This method allows to resize all components of the view.
     */
    void resizable();
}
