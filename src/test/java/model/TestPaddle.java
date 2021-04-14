package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.entities.Ball;
import model.entities.Paddle;
import model.utilities.Angle;
import model.utilities.DirVector;
import model.utilities.ObjectInit;

public class TestPaddle {

    /**
     * create a ball and check that the builder sets all the fields correctly.
     */
    @Test
    public void ballCreation() {
        final Paddle paddle = new Paddle.Builder().position(ObjectInit.PADDLE.getStartPos())
                .height(ObjectInit.PADDLE.getInitHeight())
                .width(ObjectInit.PADDLE.getInitWidth())
                .texturePath("Images/paddle/defaultPaddle.png")
                .build();
        assertEquals(ObjectInit.PADDLE.getStartPos(), paddle.getPos());
        assertEquals(new DirVector(0, 0), paddle.getDirVector());
        assertEquals(ObjectInit.PADDLE.getInitHeight(), paddle.getHeight());
        assertEquals(ObjectInit.PADDLE.getInitWidth(), paddle.getWidth());
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
      ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(Angle.RIGHT.getAngleVector().mul(-1)).height(-1);
      assertThrows(IllegalStateException.class, () -> ballBuilder.build());
      ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(Angle.MIDDLE_RIGHT.getAngleVector().mul(-1))
              .height(ObjectInit.BALL.getInitHeight()).width(-1);
      assertThrows(IllegalStateException.class, () -> ballBuilder.build());
    }
}
