package view.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.utilities.ScreenUtilities;

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
        final double screenPosX = getXInPixel(brick.getPos().getX());
        final double screenPosY = getYInPixel(brick.getPos().getY());
        final double screenWidth = getWidthInPixel(brick.getWidth());
        final double screenHeight = getHeightInPixel(brick.getHeight()); //Aggiungendo qui un / 1.5 si ottiene un immagine decisamente migliorate ma bisogna sistemare in un altro punto
        //this.graphics.drawImage(imageBrick, screenPosX, screenPosY, screenWidth, screenHeight);
        //setfill riempe il buco con un image pattern, che carica l'immagine resizata
        this.graphics.setFill(new ImagePattern(imageBrick, 0, 0, 1, 1, true));
        //adatta l'imagine alla griglia ed il problema sono le screenWidth/Height che sizano male l'image)
        this.graphics.fillRect(screenPosX, screenPosY, screenWidth, screenHeight);
        this.graphics.setStroke(Color.BLACK);
        this.graphics.strokeRect(screenPosX, screenPosY, screenWidth, screenHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPowerUp(final PowerUp pwup, final Image pwupImage) {
        final double screenPosX = getXInPixel(pwup.getPos().getX());
        final double screenPosY = getYInPixel(pwup.getPos().getY());
        final double screenWidth = getWidthInPixel(pwup.getWidth());
        final double screenHeight = getHeightInPixel(pwup.getHeight());
        this.graphics.drawImage(pwupImage, screenPosX, screenPosY, screenWidth, screenHeight);
    }

    private double getXInPixel(final double posX) {
        return posX * ScreenUtilities.REAL_X;
    }

    private double getYInPixel(final double posY) {
        return posY * ScreenUtilities.REAL_Y;
    }

    private double getWidthInPixel(final double posWidth) {
        return posWidth * ScreenUtilities.REAL_X;
    }

    private double getHeightInPixel(final double posHeight) {
        return posHeight * ScreenUtilities.REAL_Y;
    }

}
