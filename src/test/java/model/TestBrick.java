
package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.entities.Brick;

import model.utilities.BrickStatus;
import model.utilities.Position;

/**
 * test class for Brick.
 *
 */
public class TestBrick {
    private static final Position BRICK_POS = new Position(50, 50);
    private static final int BRICK_WIDTH = 10;
    private static final int BRICK_HEIGHT = 10;
    private static final int BRICK_DURABILITY = 1;
    private static final String PATH = "Images/dropPowerup/marioDropPowerUp.png";
    private static final BrickStatus STATUS = BrickStatus.DESTRUCTIBLE;

    /**
     * create a ball and check that the builder sets all the fields correctly.
     */
    @Test
    public void brickCreation() {
        final Brick brick = new Brick.Builder()
                .durability(BRICK_DURABILITY)
                .height(BRICK_HEIGHT)
                .width(BRICK_WIDTH)
                .pos(BRICK_POS)
                .status(STATUS)
                .texture(PATH)
                .build();
        assertEquals(BRICK_DURABILITY, brick.getDurability());
        assertEquals(BRICK_HEIGHT, brick.getHeight());
        assertEquals(BRICK_WIDTH, brick.getWidth());
        assertEquals(BRICK_POS, brick.getPos());
        assertEquals(STATUS, brick.getStatus());
        assertEquals(0, brick.getSpeed());
    }
}
