package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entities.Ball;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.PowerUp;
import model.entities.Wall;
import model.utilities.Boundaries;
import model.utilities.Difficulty;
import model.utilities.DirVector;
import model.utilities.ObjectInit;
import model.utilities.Position;

public class TestPowerUp {
    private static final Position POWERUP_POS = new Position(50, 50);
    private static final Position NEWPOS = new Position(50, 47);
    private static final int WALL_DIM = 600;
    private static final int POWERUP_WIDTH = 10;
    private static final int POWERUP_HEIGHT = 10;
    private static final String PATH = "Images/dropPowerup/marioDropPowerUp.png";
    private static final String PATH_POWERUP = "Images/dropPowerup/marioDropPowerUp.png";
    private static final int TIME_ELAPSED = 10;

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
     * Check that collisions occur with the game wall.
     */
    @Test
    public void checkPowerUpBoardCollision() {
        this.board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        //set powerUp pos to the bottom edge and check for a collision
        final PowerUp powerUp = new PowerUp(new Position(50, 95), 10, 10, PATH_POWERUP);
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(powerUp).get());
        //set paddle pos to in the middle of the world and check for no collision;
        powerUp.setPos(new Position(5,50));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(powerUp));
    }

    /**
     * Test if the powerup drops correctly.
     */
    @Test
    public void testPowerUpMovement() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_DIM, WALL_DIM), null);
        assertEquals(POWERUP_POS, pwup.getPos());
        //Powerup goes south direction
        final DirVector dir = new DirVector(0, -1);
        pwup.setDirVector(dir);
        board.setPowerUps(Arrays.asList(pwup));
        assertEquals(POWERUP_POS, board.getPowerUp().stream().findFirst().get().getPos());
        board.updateState(TIME_ELAPSED);
        assertEquals(NEWPOS, board.getPowerUp().stream().findFirst().get().getPos());

    }
}
