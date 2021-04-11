package view.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
import model.utilities.GameUtilities;

public class AdapterGraphicsImpl implements AdapterGraphics {

    private final GraphicsContext graphics;
    public AdapterGraphicsImpl(final GraphicsContext graphics) {
        this.graphics = graphics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBall(final Ball ball, final Image imageBall) {
        final double screenPosX = getXInPixel(ball.getPos().getX());
        final double screenPosY = getYInPixel(ball.getPos().getY());
        final double screenWidth = getWidthInPixel(ball.getWidth());
        final double screenHeight = getHeightInPixel(ball.getHeight());
        this.graphics.drawImage(imageBall, screenPosX, screenPosY, screenWidth, screenHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPaddle(final Paddle paddle, final Image imagePaddle) {
        final double screenPosX = getXInPixel(paddle.getPos().getX());
        final double screenPosY = getYInPixel(paddle.getPos().getY());
        final double screenWidth = getWidthInPixel(paddle.getWidth());
        final double screenHeight = getHeightInPixel(paddle.getHeight());
        this.graphics.drawImage(imagePaddle, screenPosX, screenPosY, screenWidth, screenHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBrick(final Brick brick, final Image imageBrick) {
        // TODO Auto-generated method stub

    }

    private double getXInPixel(final double posX) {
        return posX * GameUtilities.REAL_X;
    }

    private double getYInPixel(final double posY) {
        return posY * GameUtilities.REAL_Y;
    }

    private double getWidthInPixel(final double posWidth) {
        return posWidth * GameUtilities.REAL_X;
    }

    private double getHeightInPixel(final double posHeight) {
        return posHeight * GameUtilities.REAL_Y;
    }
}
