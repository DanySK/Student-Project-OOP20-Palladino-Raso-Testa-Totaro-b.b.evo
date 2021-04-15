package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.entities.Wall;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.Difficulty;
import model.utilities.GameObjStatus;
import model.utilities.ObjectInit;
import model.utilities.Pair;
import model.utilities.Position;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;
import resource.routing.PaddleTexture;

public class TestBoard {

    private static final String PATH_PADDLE = "Images/paddle/defaultPaddle.png";
    private static final String PATH_BALL = "Images/ball/defaultBall.png";
    private static final int STAND_POS_X = 50;
    private static final int STAND_POS_Y = 50;
    private static final int WALL_COST = 600;
    private final List<PowerUp> listPwUp = new ArrayList<>();

    private final GameBoard board = new GameBoardImpl(new Wall(200, 200), null);
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
                                          .path(PATH_BALL)
                                          .build();
    private final Paddle paddle = new Paddle.Builder()
                                      .position(ObjectInit.PADDLE.getStartPos())
                                      .height(ObjectInit.PADDLE.getInitHeight())
                                      .width(ObjectInit.PADDLE.getInitWidth())
                                      .texturePath(PATH_PADDLE)
                                      .build();

    private final PowerUp pwUp = new PowerUp(new Position(270, 510), null, 0.4, 10, 10, null, null, null, null, null);

//    /**
//     * check that by inserting a number of ball in the board
//     * they have been inserted correctly.
//     */
//    @Test
//    public void insertBallTest() {
//        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
//        final Ball.Builder ballBuilder = new Ball.Builder();
//        ballBuilder.position(new Position(STAND_POS_X, STAND_POS_Y))
//                 .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
//                 .height(ObjectInit.BALL.getInitHeight())
//                 .width(ObjectInit.BALL.getInitWidth())
//                 .speed(Difficulty.HARD.getBallVelocity())
//                 .path(BallTexture.BALL_DEFAULT.getPath())
//                 .build();
//        assertTrue(board.getSceneEntities().isEmpty());
//        board.setBalls(IntStream.range(0, 100)
//                                .mapToObj(i -> ballBuilder.build())
//                                .collect(Collectors.toList()));
//        assertEquals(100, board.getSceneEntities().size());
//    }
//
//    /**
//     * check that by inserting a number of brick in the world 
//     * they have been inserted correctly.
//     */
//    @Test
//    public void insertBrickTest() {
//        final Brick.Builder brickBuilder = new Brick.Builder();
//        brickBuilder.setPos(new Position(10, 10))
//                    .setHeight(10)
//                    .setWidth(10)
//                    .setTexture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
//                    .setStatus(GameObjStatus.NOT_DESTRUCTIBLE)
//                    .setDurability(1)
//                    .build();
//        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
//        assertTrue(board.getSceneEntities().isEmpty());
//        board.setBricks(IntStream.range(0, 100)
//                                 .mapToObj(i -> brickBuilder.build())
//                                 .collect(Collectors.toList()));
//        assertEquals(100, board.getSceneEntities().size());
//    }
//
//    /**
//     * check that by inserting a number of player in the world 
//     * they have been inserted correctly.
//     */
//    @Test
//    public void insertPaddleTest() {
//        final Paddle.Builder paddle = new Paddle.Builder();
//        paddle.position(ObjectInit.PADDLE.getStartPos())
//            .height(ObjectInit.PADDLE.getInitHeight())
//            .width(ObjectInit.PADDLE.getInitWidth())
//            .texturePath(PaddleTexture.PADDLE_DEFAULT.getPath())
//            .build();
//        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
//        assertTrue(board.getSceneEntities().isEmpty());
//        board.setPaddle(paddle.build());
//        assertEquals(100, board.getSceneEntities().size());
//    }

    /**
     * check that by inserting a number of powerUp in the world 
     * they have been inserted correctly.
     */
    @Test
    public void insertPowerUpTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        powerUpCreation();
        /*final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.position(new Position(STAND_POS_X, STAND_POS_Y))
                 .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                 .height(ObjectInit.BALL.getInitHeight())
                 .width(ObjectInit.BALL.getInitWidth())
                 .speed(Difficulty.HARD.getBallVelocity())
                 .path(BallTexture.BALL_DEFAULT.getPath())
                 .build();*/
        assertTrue(board.getSceneEntities().isEmpty());
        this.listPwUp.addAll(IntStream.range(0, 100)
                .mapToObj(i -> powerUpCreation())
                .collect(Collectors.toList()));
        board.setPowerUps(this.listPwUp);
        assertEquals(100, board.getSceneEntities().size());
    }

    private PowerUp powerUpCreation() {
        return new PowerUp(new Position(270, 510), null, 0.4, 10, 10, null, null, null, null, null);
    }

    /**
     * check that by inserting different types 
     * of game objects they are inserted correctly.
     */
    @Test
    public void insertEntityTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        board.setBalls(Arrays.asList(ball));
        board.setPaddle(paddle);
        board.setBricks(Arrays.asList(brick));
        board.setPowerUps(Arrays.asList(pwUp));
        assertEquals(4, board.getSceneEntities().size());
    }

    /**
     * Check that collisions occur with the game wall.
     */
    @Test
    public void checkBoardCollision() {
        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.position(new Position(95, 50))
            .direction(Angle.LEFT.getAngleVector().mul(-1))
            .height(ObjectInit.BALL.getInitHeight())
            .width(ObjectInit.BALL.getInitWidth())
            .speed(Difficulty.HARD.getBallVelocity())
            .path(PATH_BALL);
        board.setBalls(Arrays.asList(ballBuilder.build()));
        //set ball pos to the right edge and check for a collision
        assertEquals(Boundaries.SIDE_RIGHT, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to the bottom edge and check for a collision
        ballBuilder.position(new Position(50, 95));
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to the top edge and check for a collision
        ballBuilder.position(new Position(50, -5));
        assertEquals(Boundaries.UPPER, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to the left edge and check for a collision
        ballBuilder.position(new Position(-5, 50));
        assertEquals(Boundaries.SIDE_LEFT, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to in the middle of the world and check for no collision;
        ballBuilder.position(new Position(50, 50));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(ballBuilder.build()));
    }

    /**
     * 
     */
   @Test
    public void checkBrickCollision() {
       brick.setPos(new Position(50, 50));
       ball.setPos(new Position(55, 30));
       board.setBricks(Arrays.asList(this.brick));
       board.setBalls(Arrays.asList(this.ball));
       //fill the map of the last presence areas of the bricks
       Optional<Pair<Brick, Boundaries>> collisionResult = board.checkBallCollisionsWithBrick(board.getBalls().stream().findFirst().get());
       System.out.println(board.getBricks().stream().findFirst().get().getHit());
       assertEquals(Optional.empty(), collisionResult);
       board.getBalls().stream().findFirst().get().setPos(new Position(55, 45));
       //set ball pos on the same axis perpendicular to the brick wall and check the collisions
       collisionResult = board.checkBallCollisionsWithBrick(board.getBalls().stream().findFirst().get());
       assertTrue(collisionResult.isPresent());
       assertEquals(Boundaries.UPPER, collisionResult.get().getY());
       assertEquals(this.brick, collisionResult.get().getX());
    }

    /**
     * 
    */
    @Test
    public void checkBallPaddleCollision() {

    }

    /**
     * 
    */
    @Test
    public void checkPowerUpPaddleCollision() {

    }
}
