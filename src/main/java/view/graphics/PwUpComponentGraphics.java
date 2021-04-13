package view.graphics;

import javafx.scene.image.Image;
import model.entities.GameObject;
import model.entities.PowerUp;
import resource.routing.PersonalImages;

public class PwUpComponentGraphics implements ComponentGraphics {

    private final Image pwupImage;

    public PwUpComponentGraphics(final String tPath) {
        this.pwupImage = new Image(ClassLoader.getSystemResourceAsStream(tPath));
    }

    /**
     *  draw the paddle by passing the specific graphic information to the graphic adapter. 
     */
    @Override
    public void update(final GameObject pwup, final AdapterGraphics graphicsAdapt) {
        graphicsAdapt.drawPowerUp((PowerUp) pwup, this.pwupImage);
    }

}
