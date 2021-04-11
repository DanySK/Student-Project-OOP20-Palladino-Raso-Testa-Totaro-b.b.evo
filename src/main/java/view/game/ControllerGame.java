package view.game;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import model.utilities.GameUtilities;
import resource.routing.BackGround;
import view.graphics.AdapterGraphics;
import view.graphics.AdapterGraphicsImpl;
import model.entities.GameObject;

public class ControllerGame implements Initializable {

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblPlay;

    @FXML
    private Label lblEsc;

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
        this.canvas.setWidth(GameUtilities.CANVAS_WIDTH);
        this.canvas.setHeight(GameUtilities.CANVAS_HEIGHT);
        this.panel.setMinWidth(GameUtilities.CANVAS_WIDTH);
        this.panel.setMaxWidth(GameUtilities.CANVAS_WIDTH);
        this.panel.setMinHeight(GameUtilities.CANVAS_HEIGHT);
        this.panel.setMaxHeight(GameUtilities.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.loadAnimation();
    }

    private void loadAnimation() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
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
        drawScoreAndLives(score, lives);
        drawWorld(gameEntities);
    }

    /**
     * calls the graphic component of the game entities by updating them, 
     * which will be drawn on the canvas.
     * @param gameEntities to draw
     */
    private void drawWorld(final Set<GameObject> gameEntities) {
        gc.clearRect(0, 0, GameUtilities.CANVAS_WIDTH, GameUtilities.CANVAS_HEIGHT);
        final AdapterGraphics ga = new AdapterGraphicsImpl(gc);
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
    private void drawScoreAndLives(final Integer score, final Integer lives) {
        this.lblScore.setText("SCORE: " + score.toString());
        this.lblLives.setText("LIVES: " + lives.toString());
    }

    /**
     * set the background image of the canvas.
     * @param backGround use
     */
    public void setBackgroundImage(final BackGround backGround) {
        final BackgroundImage bg = new BackgroundImage(new Image(backGround.getPath(), 
                                                    GameUtilities.CANVAS_WIDTH,
                                                    GameUtilities.CANVAS_HEIGHT,
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
