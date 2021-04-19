package controller.input;

import controller.game.GameStatus;
import controller.game.GameController;
import javafx.scene.canvas.Canvas;

public class InputEventImpl implements InputEvent {

    private final GameController state;
    private final ControllerInput controller;
    private final Canvas canvas;

    public InputEventImpl(final Canvas canvas, final ControllerInput controller, final GameController state) {
        this.state = state;
        this.canvas = canvas;
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent() {
        isPressing();
        stoppedPressing();
    }

    /**
     * Change player status and game status based on the key pressed.
     */
    private void isPressing() {
        this.canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case LEFT:
                this.controller.setMoveLeft(true);
                break;
            case RIGHT:
                this.controller.setMoveRight(true);
                break;
            case ESCAPE:
                this.state.setPhase(GameStatus.MENU);
                break;
            case SPACE:
                if (this.state.getPhase().equals(GameStatus.PAUSE)) {
                    this.state.setPhase(GameStatus.RUNNING);
                }
                break;
            default:
                break;
            }
        });
    }

    /**
     * Change player status based on the key released.
     */
    private void stoppedPressing() {
        this.canvas.setOnKeyReleased(e -> {
            switch (e.getCode()) {
            case LEFT:
                this.controller.setMoveLeft(false);
                break;
            case RIGHT:
                this.controller.setMoveRight(false);
                break;
            default:
                break;
            }
        });
    }
}
