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
import resource.routing.PowerUpDropTexture;
import resource.routing.PowerUpTexture;

public class TestPowerUp {
    private static final Position POWERUP_POS = new Position(50, 50);
    private static final Position POWERUP_POS_COLLISION_WALL = new Position(50, 95);
    private static final Position NEWPOS = new Position(50, 47);
    private static final int WALL_DIM = 600;
    private static final int POWERUP_WIDTH = 10;
    private static final int POWERUP_HEIGHT = 10;
    private static final String PATH = "Images/powerup/defaultPowerUp.png";
    private static final String PATH_POWERUP = "Images/dropPowerup/defaultDropPowerUp.png";
    private static final int TIME_ELAPSED = 10;

    private GameBoard board;
    private PowerUp pwup;

    @Test
    private PowerUp createPowerUp() {
        final String brickTexturePath = PowerUpTexture.getThemeNameByPath(PATH).getTheme();
        return new PowerUp(POWERUP_POS, POWERUP_HEIGHT, POWERUP_WIDTH, 
                PowerUpDropTexture.getPowerUpDropTextureByName(brickTexturePath).getPath());
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
        this.pwup.setPos(POWERUP_POS_COLLISION_WALL);
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(this.pwup).get());
        //set paddle pos to in the middle of the world and check for no collision;
        this.pwup.setPos(new Position(5,50));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(this.pwup));
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
        assertEquals(POWERUP_POS, board.getPowerUps().stream().findFirst().get().getPos());
        board.updateState(TIME_ELAPSED);
        //assertEquals(NEWPOS, board.getPowerUp().stream().findFirst().get().getPos());

    }
}
