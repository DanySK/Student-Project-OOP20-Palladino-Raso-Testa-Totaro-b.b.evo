package view.graphics;

import javafx.scene.image.Image;
import model.entities.Ball;
import model.entities.GameObject;
import resource.routing.PersonalImages;

public class BallComponentGraphics implements ComponentGraphics {

    private final Image imageBall;

    public BallComponentGraphics() {
        this.imageBall = new Image(PersonalImages.BALL_IMG.getResourceAsStream());
    }

    /**
     * draw the ball by passing the specific graphic information to the graphic adapter. 
     */
    @Override
    public void update(final GameObject obj, final AdapterGraphics graphicsAdapt) {
        graphicsAdapt.drawBall((Ball) obj, imageBall);
    }

}
