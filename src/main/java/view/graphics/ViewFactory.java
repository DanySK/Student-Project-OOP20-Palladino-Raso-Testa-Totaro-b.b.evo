package view.entities;

/**
 * An interface which allows to creates a views for each entities in the game.
 */
public interface ViewFactory {
    /**
     * Get a new ball.
     * 
     * @return a new {@link MovableView} ball.
     */
    MovableView ball();
}
