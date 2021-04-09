package controller.input;

import controller.game.GamePhase;
import controller.game.GameState;
import javafx.scene.canvas.Canvas;

public class InputEventImpl implements InputEvent {

    private final GameState state;
    private final ControllerInput controller;
    private final Canvas canvas;

    public InputEventImpl(final Canvas canvas, final ControllerInput controller, final GameState state) {
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
    private void stoppedPressing() {
        this.canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case LEFT:
                this.controller.setMoveLeft(true);
                break;
            case RIGHT:
                this.controller.setMoveRight(true);
                break;
            case ESCAPE:
                this.state.setPhase(GamePhase.MENU);
                break;
            case SPACE:
                if (this.state.getPhase().equals(GamePhase.PAUSE)) {
                    this.state.setPhase(GamePhase.RUNNING);
                }
                break;
            default:
                break;
            }
        });
    }

    private void isPressing() {
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
