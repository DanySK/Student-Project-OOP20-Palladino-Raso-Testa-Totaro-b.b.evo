package view.entities;

import javafx.scene.image.Image;
import model.utilities.GameObjectType;
import view.utilities.PersonalImages;

public class ViewFactoryImpl implements ViewFactory {

    private static final int WIDTHIMAGE = 30;
    private static final int HEIGHTIMAGE = 30;

    /**
     * {@inheritDoc}
     */

    @Override
    public MovableView ball() {
        return new MovableGameObjView(GameObjectType.BALL, WIDTHIMAGE, HEIGHTIMAGE,
                new Image(PersonalImages.CURSOR_PACMAN_IMG.getResourceAsStream()));
        //return null;
    }

}
