package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.entities.Wall;
import model.utilities.Angle;
import model.utilities.Difficulty;
import model.utilities.GameObjStatus;
import model.utilities.ObjectInit;
import model.utilities.Position;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;
import resource.routing.PaddleTexture;

public class TestBoard {

    private static final int STAND_POS_X = 50;
    private static final int STAND_POS_Y = 50;
    private static final int WALL_COST = 600;

    private final GameBoard world = new GameBoardImpl(new Wall(200, 200), null);
    private final Brick brick = new Brick.Builder().setPos(new Position(STAND_POS_X, STAND_POS_Y))
                                             .setHeight(10)
                                             .setWidth(10)
                                             .setTexture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
                                             .setStatus(GameObjStatus.NOT_DESTRUCTIBLE)
                                             .setDurability(1)
                                             .build();
    private final Ball ball = new Ball.Builder().position(new Position(STAND_POS_X, STAND_POS_Y))
                                          .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                                          .height(ObjectInit.BALL.getInitHeight())
                                          .width(ObjectInit.BALL.getInitWidth())
                                          .speed(Difficulty.NORMAL.getBallVelocity())
                                          .build();
    private final Paddle paddle = new Paddle.Builder()
                                      .position(ObjectInit.PADDLE.getStartPos())
                                      .height(ObjectInit.PADDLE.getInitHeight())
                                      .width(ObjectInit.PADDLE.getInitWidth())
                                      .texturePath(PaddleTexture.PADDLE_DEFAULT.getPath())
                                      .build();

    /**
     * check that by inserting a number of ball in the board
     * they have been inserted correctly.
     */
    @Test
    public void insertBallTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.position(new Position(STAND_POS_X, STAND_POS_Y))
                 .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                 .height(ObjectInit.BALL.getInitHeight())
                 .width(ObjectInit.BALL.getInitWidth())
                 .speed(Difficulty.HARD.getBallVelocity())
                 .path(BallTexture.BALL_DEFAULT.getPath())
                 .build();
        assertTrue(board.getSceneEntities().isEmpty());
        board.setBalls(IntStream.range(0, 100)
                                .mapToObj(i -> ballBuilder.build())
                                .collect(Collectors.toList()));
        assertEquals(100, board.getSceneEntities().size());
    }

    /**
     * check that by inserting a number of brick in the world 
     * they have been inserted correctly.
     */
    @Test
    public void insertBrickTest() {
        final Brick.Builder brickBuilder = new Brick.Builder();
        brickBuilder.setPos(new Position(10, 10))
                    .setHeight(10)
                    .setWidth(10)
                    .setTexture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
                    .setStatus(GameObjStatus.NOT_DESTRUCTIBLE)
                    .setDurability(1)
                    .build();
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        board.setBricks(IntStream.range(0, 100)
                                 .mapToObj(i -> brickBuilder.build())
                                 .collect(Collectors.toList()));
        assertEquals(100, board.getSceneEntities().size());
    }

    /**
     * check that by inserting a number of player in the world 
     * they have been inserted correctly.
     */
    @Test
    public void insertPaddleTest() {
        final Paddle.Builder paddle = new Paddle.Builder();
        paddle.position(ObjectInit.PADDLE.getStartPos())
            .height(ObjectInit.PADDLE.getInitHeight())
            .width(ObjectInit.PADDLE.getInitWidth())
            .texturePath(PaddleTexture.PADDLE_DEFAULT.getPath())
            .build();
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        board.setPaddle(paddle.build());
        assertEquals(100, board.getSceneEntities().size());
    }

    /**
     * check that by inserting different types 
     * of game objects they are inserted correctly.
     */
    @Test
    public void insertEntityTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.position(new Position(STAND_POS_X, STAND_POS_Y))
                .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                .height(ObjectInit.BALL.getInitHeight())
                .width(ObjectInit.BALL.getInitWidth())
                .speed(Difficulty.HARD.getBallVelocity())
                .path(BallTexture.BALL_DEFAULT.getPath())
                .build();
       final Brick.Builder brickBuilder = new Brick.Builder();
       brickBuilder.setPos(new Position(10, 10))
                   .setHeight(10)
                   .setWidth(10)
                   .setTexture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
                   .setStatus(GameObjStatus.NOT_DESTRUCTIBLE)
                   .setDurability(1)
                   .build();
        final Paddle.Builder playerBuilder = new Paddle.Builder();
        playerBuilder.position(ObjectInit.PADDLE.getStartPos())
                    .height(ObjectInit.PADDLE.getInitHeight())
                    .width(ObjectInit.PADDLE.getInitWidth())
                    .texturePath(PaddleTexture.PADDLE_DEFAULT.getPath())
                    .build();
        board.setBalls(Arrays.asList(ballBuilder.build()));
        board.setPaddle(playerBuilder.build());
        board.setBricks(Arrays.asList(brickBuilder.build()));
        assertEquals(3, board.getSceneEntities().size());
    }

    /**
     * 
     */
    @Test
    public void checkBoardCollision() {

    }

    /**
     * 
     */
   @Test
    public void checkBrickCollision() {

    }

    /**
     * 
    */
    @Test
    public void checkPaddleCollision() {

    }
}
