package model.physics;

import model.entities.GameBoardImpl;
import model.entities.GameObject;

public interface ComponentPhysics {

    /**
     * Represents the velocity of entities. The lower the value, the slower they will go. And viceversa.
     */
    double COST_VELOCITY = 0.001;

    /**
     * Updates the physical component of the entity passed in input.
     * @param timeElapsed time elapsed from game loop.
     * @param gameObject entity of the game.
     * @param board board.
     */
    void update(int timeElapsed, GameObject gameObject, GameBoardImpl board);
}
