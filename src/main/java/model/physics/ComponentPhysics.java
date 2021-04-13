package model.physics;

import model.entities.GameBoardImpl;
import model.entities.GameObject;

public interface ComponentPhysics {

    /**
     * Updates the physical component of the entity passed in input.
     * @param timeElapsed time elapsed from game loop.
     * @param gameObject entity of the game.
     * @param board board.
     */
    void update(double timeElapsed, GameObject gameObject, GameBoardImpl board);
}
