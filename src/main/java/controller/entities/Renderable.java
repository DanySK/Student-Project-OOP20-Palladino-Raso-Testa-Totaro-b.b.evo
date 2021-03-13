package controller.entities;

import javafx.scene.Scene;

/**
 * FunctionalInterface An interface that allows to render a view trough the
 * controller.
 */
public interface Renderable {
    /**
     * Method for render the view trough the controller.
     * 
     * @param g the graphics to render.
     */
    void render(Scene g);
    //da vedere perche non so il tipo di java fx che ci va
}
