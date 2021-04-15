package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.PowerUp;
import model.entities.Wall;
import model.utilities.Position;

public class TestPowerUp {
    private static final Position POWERUP_POS = new Position(50, 50);
    private static final int WALL_DIM = 600;
    private static final int POWERUP_WIDTH = 10;
    private static final int POWERUP_HEIGHT = 10;
    private static final String PATH = "Images/dropPowerup/marioDropPowerUp.png";
    private GameBoard board;
    private PowerUp pwup;

    @Test
    private PowerUp createPowerUp() {
        return new PowerUp(POWERUP_POS, 10, 10, PATH);
    }

    /**
     * Initialize fields before the test start.
     */
    @BeforeEach
    public void createEntity() {
        this.board = new GameBoardImpl(new Wall(WALL_DIM, WALL_DIM), null);
        this.pwup = createPowerUp();
    }

    /**
     * Check that the constructor sets all the fields correctly.
     */
    @Test
    public void checkPowerUpCreation() {
        assertEquals(POWERUP_POS, pwup.getPos());
        assertEquals(POWERUP_WIDTH, pwup.getWidth());
        assertEquals(POWERUP_HEIGHT, pwup.getHeight());
    }

    /**
     * Test collisions with board boundaries.
     */
     /* @Test
    public void testCollisionWithBoundaries() {
        assertFalse(board.checkGameObjCollisionsWithWall(pwup).isPresent());
        //assertTrue(board.checkGameObjCollisionsWithWall(pwup).isPresent());
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(pwup).get());
    }*/

    /**
     * Test if the ControllerInput notify correctly the movement of the player
     * and if it moves depending on speed of player and time elapsed given by the gameLoop.
     */
    @Test
    public void testPowerUpMovement() {
        //move paddle right
        assertEquals(POWERUP_POS, pwup.getPos());

    }
}
