package view.graphics;

import javafx.scene.image.Image;
import model.entities.Brick;
import model.entities.GameObject;
import resource.routing.PersonalImages;

public class BrickComponentGraphics implements ComponentGraphics {

    private final Image brickImage;

    public BrickComponentGraphics(final String texturePath) {
        this.brickImage = new Image(ClassLoader.getSystemResourceAsStream(texturePath));
    }

    /**
     *  draw the paddle by passing the specific graphic information to the graphic adapter. 
     */
    @Override
    public void update(final GameObject obj, final AdapterGraphics graphicsAdapt) {
        graphicsAdapt.drawBrick((Brick) obj, this.brickImage);
    }

}
