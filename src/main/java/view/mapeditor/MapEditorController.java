package view.mapeditor;

import controller.menu.SceneLoader;
import controller.texture.TextureController;
import controller.utilities.CheckAlertController;
import controller.utilities.GUIController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.entities.GameObjectEmpty;
import model.mapeditor.LevelBuilder;
import model.mapeditor.LevelManager;
import model.mapeditor.LevelSelection;
import model.utilities.GameObjStatus;
import model.utilities.GameUtilities;
import model.utilities.Pair;
import resource.routing.BackGround;
import resource.routing.BrickTexture;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;

public class MapEditorController implements GUIController {

    private static final int NOT_BUILDABLE_ZONE = 4; // Number of rows where the player can't put brick
    private int rowsY;
    private GraphicsContext graphicsContext;
    private LevelBuilder levelBuilder;
    private final CheckAlertController alert = new CheckAlertController();

    @FXML
    private Pane pane;

    @FXML
    private Label creativeModeLbl;

    @FXML
    private Label ballLbl;

    @FXML
    private ComboBox<String> ballTexture;

    @FXML
    private Label paddleLbl;

    @FXML
    private ComboBox<String> paddleTexture;

    @FXML
    private Label brickLbl;

    @FXML
    private ComboBox<String> brickTexture;

    @FXML
    private Label powerupLbl;

    @FXML
    private ComboBox<String> powerupTexture;

    @FXML
    private CheckBox unbreakableCheck;

    @FXML
    private Label backGroundLlb;

    @FXML
    private ComboBox<String> backGround;

    @FXML
    private Label durabilityLbl;

    @FXML
    private Slider durabilitySet;

    @FXML
    private Label soundtrackLbl;

    @FXML
    private ComboBox<String> soundtrack;

    @FXML
    private Button menu;

    @FXML
    private Button build;

    @FXML
    private Button clear;

    @FXML
    private Label levelNameLbl;

    @FXML
    private TextField levelName;

    @FXML
    private Canvas canvas;

    /**
     * Initialize the level customization and set the mouse listeners on the canvas.
     */
    @FXML
    public void initialize() {

        final TextureController tc = new TextureController(ballTexture, paddleTexture, brickTexture, powerupTexture);
        tc.loadBallTexture();
        tc.loadPaddleTexture();
        tc.loadBrickTexture();
        tc.loadPowerupTexture();

        this.soundtrack.getItems().addAll(PersonalSounds.getSongLevelNames());
        this.backGround.getItems().addAll(BackGround.getBackGroundNames());
        this.levelBuilder = new LevelBuilder();

        this.setCanvas();
        this.canvas.setOnMouseClicked(e -> {
            if (e.getY() < (rowsY * (GameUtilities.BRICK_NUMBER_Y - NOT_BUILDABLE_ZONE))) {
                final Pair<GameObjectEmpty, Boolean> res = levelBuilder.brickSelected(e.getX(), e.getY(),
                                               brickTexture.getValue(),
                                               unbreakableCheck.isSelected() ? GameObjStatus.NOT_DESTRUCTIBLE : GameObjStatus.DESTRUCTIBLE,
                                               (int) durabilitySet.getValue());
                if (res.getY()) {
                    graphicsContext.setFill(new ImagePattern(new Image(BrickTexture.getBrickTextureByName(brickTexture.getValue())), 0, 0, 1, 1, true));
                    graphicsContext.fillRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                    graphicsContext.strokeRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                } else {
                    graphicsContext.clearRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                    graphicsContext.strokeRect(res.getX().getPos().getX(), res.getX().getPos().getY(), res.getX().getWidth(), res.getX().getHeight());
                }
            }
        });
    }

    /**
     * Draw a grid on canvas.
     */
    private void setCanvas() {
        this.canvas.setWidth(GameUtilities.CANVAS_WIDTH);
        this.canvas.setHeight(GameUtilities.CANVAS_HEIGHT);
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.graphicsContext.setStroke(Color.BLACK);
        this.graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        final int colsX = (int) (this.canvas.getWidth() / GameUtilities.BRICK_NUMBER_X);
        this.rowsY = (int) (this.canvas.getHeight() / GameUtilities.BRICK_NUMBER_Y);
        final double wastePixel = GameUtilities.CANVAS_WIDTH % GameUtilities.BRICK_NUMBER_X;
        int currentYpos = 0;
        for (int i = 0; i < GameUtilities.BRICK_NUMBER_Y; i++) {
            graphicsContext.strokeLine(0, currentYpos, canvas.getWidth() - wastePixel, currentYpos);
            currentYpos += rowsY;
        }
        int currentXpos = 0;
        for (int i = 0; i < GameUtilities.BRICK_NUMBER_X; i++) {
            graphicsContext.strokeLine(currentXpos, 0, currentXpos, canvas.getHeight());
            currentXpos += colsX;
        }
        this.graphicsContext.strokeLine(currentXpos, 0, currentXpos, canvas.getHeight());
        this.graphicsContext.setFill(Color.BLACK);
        this.graphicsContext.fillRect(0, rowsY * (GameUtilities.BRICK_NUMBER_Y - NOT_BUILDABLE_ZONE), canvas.getWidth() - wastePixel, canvas.getHeight());
    }

    /**
     * @param event when click the button menu the user return to the Main Menu
     */
    @FXML
    void backToMenu(final MouseEvent event) {
        SceneLoader.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), 
                PersonalViews.SCENE_MAIN_MENU.getURL(), 
                PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                this.pane.getWidth(), 
                this.pane.getHeight(),
                PersonalStyle.DEFAULT_STYLE.getStylePath());
    }

    /**
     * Check if the forms have been filled correctly.
     * Call the level builder to create a level with elem.
     * @param event
     */
    @FXML
    public void buildLvl(final MouseEvent event) {
        if (levelName.getText().isBlank() || soundtrack.getValue() == null || backGround.getValue() == null) {
            alert.checkAllField();
        } else if (LevelSelection.isStandardLevel(levelName.getText())) {
            alert.checkLevelName();
        } else {
            this.levelBuilder.setLevelName(levelName.getText());
            this.levelBuilder.setBackGround(backGround.getValue());
            this.levelBuilder.setMusic(soundtrack.getValue());
            this.levelBuilder.setBall(ballTexture.getValue());
            this.levelBuilder.setPaddle(paddleTexture.getValue());
            LevelManager.saveLevel(this.levelBuilder.build());
            alert.checkLevelCreate();
        }
    }

    /**
     * Clear all the elements on the grid.
     * @param event
     */
    @FXML
    public void clearAll(final MouseEvent event) {
        levelBuilder.deleteAll();
        this.setCanvas();
    }
}
