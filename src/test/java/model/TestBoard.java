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
import model.utilities.BrickStatus;
import model.utilities.ObjectInit;
import model.utilities.Pair;
import model.utilities.Position;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;
import resource.routing.PaddleTexture;

public class TestBoard {

    private static final String PATH_PADDLE = "Images/paddle/defaultPaddle.png";
    private static final String PATH_BALL = "Images/ball/defaultBall.png";
    private static final String PATH_POWERUP= "Images/dropPowerup/marioDropPowerUp.png";
    private static final int STAND_POS_X = 50;
    private static final int STAND_POS_Y = 50;
    private static final int WALL_COST = 600;
    private final List<PowerUp> listPwUp = new ArrayList<>();

    private final GameBoard board = new GameBoardImpl(new Wall(200, 200), null);
    private final Brick brick = new Brick.Builder().setPos(new Position(STAND_POS_X, STAND_POS_Y))
                                             .setHeight(10)
                                             .setWidth(10)
                                             .setTexture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
                                             .setStatus(BrickStatus.NOT_DESTRUCTIBLE)
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

    private final PowerUp pwUp = new PowerUp(new Position(270, 510), 10, 10, PATH_POWERUP);

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
        assertTrue(board.getSceneEntities().isEmpty());
        this.listPwUp.addAll(IntStream.range(0, 100)
                .mapToObj(i -> powerUpCreation())
                .collect(Collectors.toList()));
        board.setPowerUps(this.listPwUp);
        assertEquals(100, board.getSceneEntities().size());
    }

    private PowerUp powerUpCreation() {
        return new PowerUp(new Position(270, 510), 10, 10, PATH_POWERUP);
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
    public void checkBallBoardCollision() {
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
     * Check that collisions occur with the game wall.
     */
    @Test
    public void checkPaddleBoardCollision() {
        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        final Paddle.Builder paddleBuilder = new Paddle.Builder();
        paddleBuilder.position(new Position(23, 50))
            .height(ObjectInit.PADDLE.getInitHeight())
            .width(ObjectInit.PADDLE.getInitWidth())
            .texturePath(PATH_PADDLE);
        board.setPaddle(paddleBuilder.build());
        //set paddle pos to the right edge and check for a collision
        assertEquals(Boundaries.SIDE_RIGHT, board.checkGameObjCollisionsWithWall(paddleBuilder.build()).get());
        //set paddle pos to the left edge and check for a collision
        paddleBuilder.position(new Position(-5, 50));
        assertEquals(Boundaries.SIDE_LEFT, board.checkGameObjCollisionsWithWall(paddleBuilder.build()).get());
        //set paddle pos to in the middle of the world and check for no collision;
        paddleBuilder.position(new Position(5, 50));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(paddleBuilder.build()));
    }

    /**
     * Check that collisions occur with the game wall.
     */
    @Test
    public void checkPowerUpBoardCollision() {
        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        //set powerUp pos to the bottom edge and check for a collision
        final PowerUp powerUp = new PowerUp(new Position(50, 95), 10, 10, PATH_POWERUP);
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(powerUp).get());
        //set paddle pos to in the middle of the world and check for no collision;
        powerUp.setPos(new Position(5,50));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(powerUp));
    }

    /**
     * 
     * Check that by positioning the ball in contact with a brick,
     * a collision is detected based on the wall where it occurs.
     */
   @Test
    public void checkBrickCollision() {
       brick.setPos(new Position(50, 50));
       ball.setPos(new Position(55, 30));
       board.setBricks(Arrays.asList(this.brick));
       board.setBalls(Arrays.asList(this.ball));
       //fill the map of the last presence areas of the bricks
       Optional<Pair<Brick, Boundaries>> collisionResult = board.checkBallCollisionsWithBrick(board.getBalls().stream().findFirst().get());
       assertEquals(Optional.empty(), collisionResult);
       board.getBalls().stream().findFirst().get().setPos(new Position(55, 45));
       //set ball pos on the same axis perpendicular to the brick wall and check the collisions
       collisionResult = board.checkBallCollisionsWithBrick(board.getBalls().stream().findFirst().get());
       assertTrue(collisionResult.isPresent());
       assertEquals(Boundaries.UPPER, collisionResult.get().getY());
       assertEquals(this.brick, collisionResult.get().getX());
    }

    /**
     * Check that according to the collision zone with the player 
     * the ball has a direction. The more the collision zone will be to the right,
     * the more acute the angle will be.
    */
    @Test
    public void checkBallPaddleCollision() {
        paddle.setPos(new Position(50, 50));
        ball.setPos(new Position(55, 30));
        board.setPaddle(this.paddle);
        board.setBalls(Arrays.asList(this.ball));
        Pair<Optional<Boundaries>, Optional<Angle>> collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
        //fill the map of the last presence areas of the player
        assertEquals(Optional.empty(), collisionResult.getX());
        assertEquals(Optional.empty(), collisionResult.getY());
        board.getBalls().stream().findFirst().get().setPos(new Position(55, 45));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          //touching the top of the player will also present the direction that the ball will take after the collision
          assertTrue(collisionResult.getX().isPresent());
          assertTrue(collisionResult.getY().isPresent());
          assertEquals(Boundaries.UPPER, collisionResult.getX().get());
          assertEquals(Angle.LEFT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(60, 45));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.LEFT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(75, 45));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.MIDDLE_LEFT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(85, 45));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.MIDDLE_RIGHT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(105, 45));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.RIGHT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(120, 45));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.RIGHT, collisionResult.getY().get());
          //if the ball bounces on the player's side 
          //the direction is changed as if it had bounced on a normal vertical wall
          board.getBalls().stream().findFirst().get().setPos(new Position(30, 55));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertTrue(collisionResult.getX().isEmpty());
          assertTrue(collisionResult.getY().isEmpty());
          board.getBalls().stream().findFirst().get().setPos(new Position(45, 55));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Boundaries.SIDE_LEFT, collisionResult.getX().get());
          assertTrue(collisionResult.getY().isEmpty());
    }

    /**
     * Check that according to the collision zone with the player 
     * the ball has a direction. The more the collision zone will be to the right,
     * the more acute the angle will be.
    */
    @Test
    public void checkPowerUpPaddleCollision() {
        paddle.setPos(new Position(50, 50));
        pwUp.setPos(new Position(55, 30));
        board.setPaddle(this.paddle);
        board.setPowerUps(Arrays.asList(this.pwUp));
        Optional<Pair<PowerUp, Boundaries>> collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUp().stream().findFirst().get());
        //fill the map of the last presence areas of the player
        assertTrue(collisionResult.isEmpty());
        board.getPowerUp().stream().findFirst().get().setPos(new Position(55, 45));
        collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUp().stream().findFirst().get());
        //touching the top of the player will also present the direction that the ball will take after the collision
        assertTrue(collisionResult.isPresent());
        assertEquals(Boundaries.UPPER, collisionResult.get().getY());
        //if the ball bounces on the player's side 
        //the direction is changed as if it had bounced on a normal vertical wall
        board.getPowerUp().stream().findFirst().get().setPos(new Position(30, 55));
        collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUp().stream().findFirst().get());
        assertTrue(collisionResult.isEmpty());
        board.getPowerUp().stream().findFirst().get().setPos(new Position(45, 55));
        collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUp().stream().findFirst().get());
        assertEquals(Boundaries.SIDE_LEFT, collisionResult.get().getY());
    }
}
