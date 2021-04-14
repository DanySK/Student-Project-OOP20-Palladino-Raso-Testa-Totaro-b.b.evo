package view;

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
     *  This method allows to resize all components of the view.
     */
    void resizable();
}
