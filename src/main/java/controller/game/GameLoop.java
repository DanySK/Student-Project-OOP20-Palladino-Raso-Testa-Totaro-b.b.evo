package controller.game;

import javafx.scene.Scene;
import model.entities.GameBoard;

public class GameLoop implements Runnable {

    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;


    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();

    }



    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
