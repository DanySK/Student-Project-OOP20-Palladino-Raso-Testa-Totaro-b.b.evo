package view.graphics;

import model.entities.GameObject;
import model.entities.Paddle;
import resource.routing.PersonalImages;
import javafx.scene.image.Image;

public class PaddleComponentGraphics implements ComponentGraphics {

    private final Image paddleImage;

    public PaddleComponentGraphics() {
        this.paddleImage = new Image(PersonalImages.PADDLE_IMG.getResourceAsStream());
    }

    /**
     *  draw the paddle by passing the specific graphic information to the graphic adapter. 
     */
    @Override
    public void update(final GameObject obj, final AdapterGraphics graphicsAdapt) {
        graphicsAdapt.drawPaddle((Paddle) obj, this.paddleImage);
    }

}
