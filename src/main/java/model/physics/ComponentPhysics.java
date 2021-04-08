package model.physics;

import model.entities.GameBoardImpl;
import model.entities.GameObject;

public interface ComponentPhysics {

    /**
     * Constant used for set entity velocity.
     */
    double SCALER = 0.001;

    /**
     * Update physic component of selected entity.
     * @param timeElapsed time elapsed from game loop.
     * @param gameObject entity of the game.
     * @param board board.
     */
    void update(int timeElapsed, GameObject gameObject, GameBoardImpl board);
}
