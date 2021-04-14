package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;

public interface FXMLMenuController {

    /**
     * 
     */
    void loadListener();

    /**
     * 
     */
    void loadFont();

    /**
     * 
     */
    void loadAnimation();

    /**
     */
    EventHandler<ActionEvent> switchPage(final PersonalViews scene, final PersonalStyle style);

    /**
     * 
     */
    void resizable();
}
