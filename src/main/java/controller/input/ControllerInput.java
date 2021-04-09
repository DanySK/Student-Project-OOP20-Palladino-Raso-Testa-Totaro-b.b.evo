package controller.input;

/**
 * Interface that check if user use input device.
 */
public interface ControllerInput {

    /**
     * @return true if user try to move left.
     */
    boolean canMoveLef();

    /**
     * @return true if user try to move right.
     */
    boolean canMoveRight();

    /**
     * @param cond  The moveRight flag is set based on the "cond" parameter. (If the user tries to move right)
     */
    void setMoveRight(boolean cond);

    /**
     * @param cond  The moveLeft flag is set based on the "cond" parameter. (If the user tries to move left)
     */
    void setMoveLeft(boolean cond);
}
