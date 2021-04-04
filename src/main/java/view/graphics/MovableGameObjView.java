package view.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;
import model.utilities.Direction;
import model.utilities.GameObjectType;
import model.utilities.Status;
import view.utilities.PersonalImages;
import view.utilities.PersonalSounds;

/**
 * An implementation of {@link MovableView}.
 */
public class MovableGameObjView extends SimpleGameObjView implements MovableView {

    private Status status;
    private Direction direction;
    private final Image image;
    /**
     * Constructor that extends the constructor of {@link SimpleGameObjView}.
     * 
     * @param type   {@inheritDoc}}
     * @param width  {@inheritDoc}
     * @param height {@inheritDoc}
     * @param image the image associated with the gameObj
     */
    protected MovableGameObjView(final GameObjectType type, final int width, final int height, final Image image) {
        super(type, null, width, height);
        this.image = image;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(final Status status) {
        this.status = status;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDirection(final Direction newDir) {
        this.direction = newDir;
    }
}
