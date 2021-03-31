package view.game;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.utilities.ConstantScreen;
import model.entities.GameObject;

public class ControllerGame implements Initializable {

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblPlay;

    @FXML
    private Pane panel;

    @FXML
    private VBox dashBoard;

    /**
     * At Game.fxml load it initialize the width and height of the canvas and set his 
     * graphic context for draw entity.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.canvas.setWidth(ConstantScreen.CANVAS_WIDTH);
        this.canvas.setHeight(ConstantScreen.CANVAS_HEIGHT);
        this.panel.setMinWidth(ConstantScreen.CANVAS_WIDTH);
        this.panel.setMaxWidth(ConstantScreen.CANVAS_WIDTH);
        this.panel.setMinHeight(ConstantScreen.CANVAS_HEIGHT);
        this.panel.setMaxHeight(ConstantScreen.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * 
     * @param play
     */
    public void setPlay(final boolean play) {
        this.lblPlay.setVisible(play);
    }

    /**
     * Draw all entities of the game adapted to the current resolution.
     * @param gameEntities Collection of each game entity taken from the game loop.
     * @param score The score of the player.
     * @param highScore The top score.
     * @param lives The remained life ot the player.
     */
    public void render(final Set<GameObject> gameEntities, final int score, final int highScore, final int lives) {
        drawScoreAndLives(score, highScore, lives);
        drawWorld(gameEntities);
    }

    /**
     * calls the graphic component of the game entities by updating them, 
     * which will be drawn on the canvas.
     * @param gameEntities to draw
     */
    private void drawWorld(final Set<GameObject> gameEntities) {
        gc.clearRect(0, 0, ConstantScreen.CANVAS_WIDTH, ConstantScreen.CANVAS_HEIGHT);
        final GraphicsAdapter ga = new GraphicsAdapterImpl(gc);
        gameEntities.stream().forEach(e -> {
            e.updateGraphics(ga);
        });
    }

    /**
     * draw information about your current score and lives.
     * @param highScore
     * @param score
     * @param lives
     */
    private void drawScoreAndLives(final Integer highScore, final Integer score, final Integer lives) {
        this.lblScore.setText("SCORE: " + score.toString());
        this.lblLives.setText("LIVES: " + lives.toString());
    }

    /**
     * set the background image of the canvas.
     * @param backGround use
     */
    public void setBackGroundImage(final BackGround backGround) {
        final BackgroundImage bg = new BackgroundImage(new Image(backGround.getLocation(), 
                ConstantScreen.CANVAS_WIDTH,
                ConstantScreen.CANVAS_HEIGHT,
                false,
                true),
                BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.panel.setBackground(new Background(bg));
    }

    /**
     * 
     * @return canvas
     */
    public final Canvas getCanvas() {
        return this.canvas;
    }
}
