package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.entities.Ball;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Wall;
import model.utilities.Angle;
import model.utilities.Difficulty;
import model.utilities.DirVector;
import model.utilities.ObjectInit;
import model.utilities.Position;

public class TestBall {

  /**
  * create a ball and check that the builder sets all the fields correctly.
  */
 @Test
 public void ballCreation() {
     final Ball ball = new Ball.Builder().position(ObjectInit.BALL.getStartPos())
             .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1)).height(ObjectInit.BALL.getInitHeight())
             .width(ObjectInit.BALL.getInitWidth()).speed(Difficulty.HARD.getBallVelocity())
             .path(new String("aaaa"))                  //non c'è bisogno ora nel test
             .build();
     assertEquals(ObjectInit.BALL.getStartPos(), ball.getPos());
     assertEquals(Angle.MIDDLE_LEFT.getAngleVector().mul(-1), ball.getDirVector());
     assertEquals(Difficulty.HARD.getBallVelocity(), ball.getSpeed());
     assertEquals(ObjectInit.BALL.getInitHeight(), ball.getHeight());
     assertEquals(ObjectInit.BALL.getInitWidth(), ball.getWidth());
 }
 
/**
* create a ball with the wrong fields and check that exceptions are thrown.
*/
@Test
public void failBallCreation() {
  final Ball.Builder ballBuilder = new Ball.Builder().position(null);
  assertThrows(IllegalStateException.class, () -> ballBuilder.build());
  ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(null);
  assertThrows(IllegalStateException.class, () -> ballBuilder.build());
  ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1)).height(-1);
  assertThrows(IllegalStateException.class, () -> ballBuilder.build());
  ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
          .height(ObjectInit.BALL.getInitHeight()).width(-1);
  assertThrows(IllegalStateException.class, () -> ballBuilder.build());
}

/**
 *  put ball into the world. Update the time to see the movement of the ball.
 */
@Test
public void ballMovement() {
  final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
  final Ball.Builder ballBuilder = new Ball.Builder();
  ballBuilder.height(ObjectInit.BALL.getInitHeight()).width(ObjectInit.BALL.getInitWidth())
          .speed(Difficulty.NORMAL.getBallVelocity());
  // north direction
  double py = Math.sin(Math.toRadians(90));
  double px = Math.cos(Math.toRadians(90));
  ballBuilder.position(new Position(50, 50)).direction(new DirVector(px, py))
  .path(new String("aaaa"));
  board.setBalls(Arrays.asList(ballBuilder.build()));
  assertEquals(new Position(50, 50), board.getBalls().stream().findFirst().get().getPos());
  board.updateState(10);
  assertEquals(new Position(50, 52), board.getBalls().stream().findFirst().get().getPos());

  // south direction
  py = Math.sin(Math.toRadians(270));
  px = Math.cos(Math.toRadians(270));
  ballBuilder.position(new Position(50, 50)).direction(new DirVector(px, py));
  board.setBalls(Arrays.asList(ballBuilder.build()));
  assertEquals(new Position(50, 50), board.getBalls().stream().findFirst().get().getPos());
  board.updateState(10);
  assertEquals(new Position(50, 48), board.getBalls().stream().findFirst().get().getPos());

  // east direction
  py = Math.sin(Math.toRadians(0));
  px = Math.cos(Math.toRadians(0));
  ballBuilder.position(new Position(50, 50)).direction(new DirVector(px, py));
  board.setBalls(Arrays.asList(ballBuilder.build()));
  assertEquals(new Position(50, 50), board.getBalls().stream().findFirst().get().getPos());
  board.updateState(10);
  assertEquals(new Position(52, 50), board.getBalls().stream().findFirst().get().getPos());

  // west direction
  py = Math.sin(Math.toRadians(180));
  px = Math.cos(Math.toRadians(180));
  ballBuilder.position(new Position(50, 50)).direction(new DirVector(px, py));
  board.setBalls(Arrays.asList(ballBuilder.build()));
  assertEquals(new Position(50, 50), board.getBalls().stream().findFirst().get().getPos());
  board.updateState(10);
  assertEquals(new Position(48, 50), board.getBalls().stream().findFirst().get().getPos());
}


/*    update time in the world and check that for equal time intervals, if the ball
    has a higher speed, it will have covered a greater space.

@Test
public void ballSpeed() {
 final double py = Math.sin(Math.toRadians(0));
 final double px = Math.cos(Math.toRadians(0));
 final GameBoard world = new GameBoardImpl(new Border(100, 100), null);
 final Ball.Builder ballBuilder = new Ball.Builder();
 ballBuilder.height(ObjectInit.BALL.getInitHeight()).width(ObjectInit.BALL.getInitWidth()).position(new Position(50, 50))
         .direction(new DirVector(px, py));
 // 0 speed
 ballBuilder.speed(0);
 world.setBalls(Arrays.asList(ballBuilder.build()));
 assertEquals(new Position(50, 50), world.getBalls().stream().findFirst().get().getPos());
 world.updateState(1_000_000);
 assertEquals(new Position(50, 50), world.getBalls().stream().findFirst().get().getPos());

 // 100 speed
 ballBuilder.speed(100);
 world.setBalls(Arrays.asList(ballBuilder.build()));
 assertEquals(new Position(50, 50), world.getBalls().stream().findFirst().get().getPos());
 world.updateState(10);
 assertEquals(new Position(51, 50), world.getBalls().stream().findFirst().get().getPos());

 // 1000 speed
 ballBuilder.speed(1000);
 world.setBalls(Arrays.asList(ballBuilder.build()));
 assertEquals(new Position(50, 50), world.getBalls().stream().findFirst().get().getPos());
 world.updateState(10);
 assertEquals(new Position(60, 50), world.getBalls().stream().findFirst().get().getPos());
}*/


/*   put the ball near the edge and update time so that the ball collides with the
    edge. if the ball collides with a horizontal wall invert the component x of
    the velocity vector. if the ball collides with a vertical wall invert the
    component y of the velocity vector.

@Test
public void ballBorderPhysics() {
 // collision with border vertical
 double py = Math.sin(Math.toRadians(0));
 double px = Math.cos(Math.toRadians(0));
 final GameBoard world = new GameBoardImpl(new Border(100, 100), null);
 final Ball.Builder ballBuilder = new Ball.Builder();
 ballBuilder.height(10).width(10).position(new Position(90, 50)).direction(new DirVector(px, py)).speed(100);
 world.setBalls(Arrays.asList(ballBuilder.build()));
 assertEquals(new DirVector(px, py), world.getBalls().stream().findFirst().get().getDirVector());
 world.updateState(10);
 assertEquals(new DirVector(-px, py), world.getBalls().stream().findFirst().get().getDirVector());

 // collision with border orizontal
 px = Math.cos(Math.toRadians(225));
 py = Math.sin(Math.toRadians(255));
 ballBuilder.height(10).width(10).direction(new DirVector(px, py)).position(new Position(50, 0)).speed(100);
 world.setBalls(Arrays.asList(ballBuilder.build()));
 assertEquals(new DirVector(px, py), world.getBalls().stream().findFirst().get().getDirVector());
 world.updateState(10);
 assertEquals(new DirVector(px, -py), world.getBalls().stream().findFirst().get().getDirVector());
}*/

}
