package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.game.GameState;
import controller.game.GameStateImpl;
import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.entities.Wall;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.ObjectInit;
import model.utilities.Position;

public class TestPaddle {

    private static final String PATH = "Images/paddle/defaultPaddle.png";
    private GameBoard board;
    private Paddle p1;
    private Paddle p2;
    private Paddle p3;
    private Paddle p4;
    private final Map<Paddle, ControllerInput> inputController = new HashMap<>();

  /**
   * Initialize fields before the test start.
   */
  @BeforeEach
  public void createEntity() {
      //final GameState gameState = new GameStateImpl();
      this.board = new GameBoardImpl(new Wall(600, 600), null);
      this.p1 = createPaddle(ObjectInit.PADDLE.getStartPos());
      //this.p2 = createPlayer(new Position(600, 580));
      //this.p3 = createPlayer(new Position(-10, 580));
      //this.p4 = createPlayer(ObjectInit.PADDLE.getStartPos());
      //final Set<Paddle> playerContainer = Set.of(p1, p2, p3, p4);
      this.board.setPaddle(p1);
      this.inputController.put(p1, new ControllerInputImpl());
      //this.inputController.put(PlayerId.TWO, new KeyboardInputController());

  }

    /**
     * create a paddle and check that the builder sets all the fields correctly.
     */
    @Test
    public void paddleCreation() {
        assertEquals(ObjectInit.PADDLE.getStartPos(), p1.getPos());
        assertEquals(new DirVector(0, 0), p1.getDirVector());
        assertEquals(ObjectInit.PADDLE.getInitHeight(), p1.getHeight());
        assertEquals(ObjectInit.PADDLE.getInitWidth(), p1.getWidth());
    }

    /**
     * Test if the builder create correctly the players.
     */
    @Test
    public void testPaddleCreation() {
        assertEquals(new Position(250, 540), p1.getPos());
//      assertEquals(new P2d(190, 580), e4.getPos());
        assertEquals(78, p1.getWidth());
        assertEquals(20, p1.getHeight());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder().build());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder()
                .position(null).build());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder()
                .width(-1).build());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder()
                .height(0).build());
    }

    /**
     * Test if the collision with boundaries are correctly checked.
     */
    @Test
    public void testCollisionWithBoundaries() {
        assertFalse(board.checkGameObjCollisionsWithWall(p1).isPresent());
        //assertTrue(world.checkCollisionWithBoundaries(e2).isPresent());
        //assertTrue(world.checkCollisionWithBoundaries(e3).isPresent());
        //assertEquals(Boundaries.SIDE_LEFT, board.checkCollisionWithBoundaries(e1).get());
        //assertEquals(Collision.RIGHT, world.checkCollisionWithBoundaries(e2).get());
  }
    

    /**
     * Test if the inputController notify correctly the movement of the player with
     * different player id and if it moves depending on speed of player and delta time
     * given by the gameLoop.
     */
    @Test
    public void testPlayerMovement() {
        assertEquals(new Position(250, 540), p1.getPos());
        //assertEquals(new Position(190, 580), e4.getPos());
        inputController.get(p1).setMoveRight(true);
        //inputController.get(PlayerId.TWO).notifyMoveLeft(true);
        inputController.entrySet().forEach(i -> {
            board.movePaddle(i.getValue());
        });
        board.updateState(20);
        assertEquals(new Position(259,540), p1.getPos());
        //assertEquals(new Position(181,580), e4.getPos());
    }
    
    
    
    
//    /**
//     *  put paddle into the world. Update the time to see the movement of the paddle.
//     */
//    @Test
//    public void paddleMovement() {
//        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
//        final Paddle.Builder paddleBuilder = new Paddle.Builder();
//        paddleBuilder.height(ObjectInit.PADDLE.getInitHeight()).width(ObjectInit.PADDLE.getInitWidth())
//            .texturePath(PATH);
//
//        // west direction
//        double py = Math.sin(Math.toRadians(180));
//        double px = Math.cos(Math.toRadians(180));
//        paddleBuilder.position(new Position(50, 50)).direction(new DirVector(px, py));
//        board.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new Position(50, 50), board.getBalls().stream().findFirst().get().getPos());
//        board.updateState(10);
//        assertEquals(new Position(46, 50), board.getBalls().stream().findFirst().get().getPos());
//
//        // east direction
//        py = Math.sin(Math.toRadians(0));
//        px = Math.cos(Math.toRadians(0));
//        ballBuilder.position(new Position(50, 50)).direction(new DirVector(px, py));
//        board.setBalls(Arrays.asList(ballBuilder.build()));
//        assertEquals(new Position(50, 50), board.getBalls().stream().findFirst().get().getPos());
//        board.updateState(10);
//        assertEquals(new Position(54, 50), board.getBalls().stream().findFirst().get().getPos());
//    }
    
    private Paddle createPaddle(final Position pos) {
        return new Paddle.Builder()
                .position(pos)
                .width(78)
                .height(20)
                .texturePath(PATH)
                .build();
    }
}
