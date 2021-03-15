package view.entities;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import model.utilities.Direction;
import model.utilities.GameObjectType;
import model.utilities.Pair;
import model.utilities.Status;
//import view.utilities.BufferedImageLoader;

public class ViewFactoryImpl implements ViewFactory {

    private static final int WIDTHIMAGE = 30;
    private static final int HEIGHTIMAGE = 30;

    /**
     * {@inheritDoc}
     */

    @Override
    public MovableView ball() {
        /*
        return new MovableGameObjView(GameObjectType.BALL, WIDTHIMAGE, HEIGHTIMAGE,
                this.loadBallImages(GameObjectType.BALL.toString()));
                */
        return null;
    }

    private Map<Pair<Direction, Status>, BufferedImage> loadBallImages(final String type) {
        final Map<Pair<Direction, Status>, BufferedImage> images = new HashMap<>();
       // images.put(new Pair<>(dir, Status.CHASING), BufferedImageLoader.loadImage("Images/Ball/Yellow_Ball.png"));
        return images;
    }

}
