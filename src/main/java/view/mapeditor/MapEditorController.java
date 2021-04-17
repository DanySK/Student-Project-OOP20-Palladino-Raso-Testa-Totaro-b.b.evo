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
import model.utilities.BrickStatus;
import model.utilities.GameUtilities;
import model.utilities.Pair;
import resource.routing.BackGround;
import resource.routing.BrickTexture;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import resource.routing.PersonalViews;
import resource.routing.PowerUpTexture;

public class MapEditorController implements GUIController {

    private static final int NOT_BUILDABLE_ZONE = 4; // Number of rows where the player can't put brick
    private int rowsY;
    private GraphicsContext graphicsContext;
    private LevelBuilder levelBuilder;
    private String textureType;
    private boolean statePowerup = true;

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
    private ComboBox<String> soundtrack;

    @FXML
    private Label soundtrackLbl;

    @FXML
    private ComboBox<String> backGround;

    @FXML
    private Label backGroundLlb;

    @FXML
    private CheckBox unbreakableCheck;

    @FXML
    private Label durabilityLbl;

    @FXML
    private Slider durabilitySet;

    @FXML
    private ComboBox<String> brickTexture;

    @FXML
    private Label brickLbl;

    @FXML
    private CheckBox powerupTexture;

    @FXML
    private Button build;

    @FXML
    private Button clear;

    @FXML
    private Label levelNameLbl;

    @FXML
    private TextField levelName;

    @FXML
    private Button menu;

    @FXML
    private Canvas canvas;


    /**
     * Initialize the level customization and set the mouse listeners on the canvas.
     */
    @FXML
    public void initialize() {

        final TextureController tc = new TextureController(ballTexture, paddleTexture, brickTexture);
        tc.loadBallTexture();
        tc.loadPaddleTexture();
        tc.loadBrickTexture();

        this.soundtrack.getItems().addAll(PersonalSounds.getSongLevelNames());
        this.backGround.getItems().addAll(BackGround.getBackGroundNames());
        this.levelBuilder = new LevelBuilder();

        brickTexture.setOnAction(c -> {
            setTypeTexture(brickTexture.getValue());
        });

        this.setCanvas();
        this.canvas.setOnMouseClicked(e -> {
            if (e.getY() < (rowsY * (GameUtilities.BRICK_NUMBER_Y - NOT_BUILDABLE_ZONE))) {
                final BrickStatus state;
                if (unbreakableCheck.isSelected()) {
                    state = BrickStatus.NOT_DESTRUCTIBLE;
                } else if (powerupTexture.isSelected()) {
                    state = BrickStatus.DROP_POWERUP;
                } else {
                    state = BrickStatus.DESTRUCTIBLE;
                }

                final Pair<GameObjectEmpty, Boolean> init = levelBuilder.brickSelected(e.getX(), e.getY(),
                                                                                  getTypeTexture(),
                                                                                  state,
                                                                                  (int) durabilitySet.getValue());
                if (init.getY()) {
                    if  (unbreakableCheck.isSelected()) {
                        graphicsContext.setFill(new ImagePattern(new Image(BrickTexture.getBrickTextureByName("Undestructible")), 0, 0, 1, 1, true));
                    } else {
                        if (powerupTexture.isSelected()) {
                            graphicsContext.setFill(new ImagePattern(new Image(PowerUpTexture.getPowerUpTextureByName(brickTexture.getValue())), 0, 0, 1, 1, true));
                        } else {
                            graphicsContext.setFill(new ImagePattern(new Image(BrickTexture.getBrickTextureByName(brickTexture.getValue())), 0, 0, 1, 1, true));
                        }
                    }
                    graphicsContext.fillRect(init.getX().getPos().getX(), init.getX().getPos().getY(), init.getX().getWidth(), init.getX().getHeight());
                    graphicsContext.strokeRect(init.getX().getPos().getX(), init.getX().getPos().getY(), init.getX().getWidth(), init.getX().getHeight());
                } else {
                    graphicsContext.clearRect(init.getX().getPos().getX(), init.getX().getPos().getY(), init.getX().getWidth(), init.getX().getHeight());
                    graphicsContext.strokeRect(init.getX().getPos().getX(), init.getX().getPos().getY(), init.getX().getWidth(), init.getX().getHeight());
                }
            }
        });
    }

    /**
     * @return ff
     */
    private String getTypeTexture() {
        return textureType;
    }

    /**
     * @param textureType
     */
    private void setTypeTexture(final String textureType) {
        if (textureType.equals(brickTexture.getValue())) {
            this.textureType = brickTexture.getValue();
            statePowerup = false;
        } else {
            //this.textureType = powerupTexture.getValue();
            statePowerup = true;
        }
    }

    /**
     * @return 
     */

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
        final var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneLoader.switchScene(stage, 
                PersonalViews.SCENE_CREATIVEMODE.getURL(), 
                PersonalViews.SCENE_CREATIVEMODE.getTitleScene(), 
                stage.getWidth(), 
                stage.getHeight(),
                PersonalStyle.DEFAULT_STYLE.getStylePath());
        stage.setResizable(true);
    }

    /**
     * Check if the forms have been filled correctly.
     * Call the level builder to create a level with elem.
     */
    @FXML
    public void buildLvl() {
        if (levelName.getText().isBlank() || soundtrack.getValue() == null || backGround.getValue() == null) {
            CheckAlertController.checkAllField();
        } else if (LevelSelection.isStandardLevel(levelName.getText())) {
            CheckAlertController.checkLevelName();
        } else {
            this.levelBuilder.setLevelName(levelName.getText());
            this.levelBuilder.setMusic(soundtrack.getValue());
            this.levelBuilder.setBackGround(backGround.getValue());
            this.levelBuilder.setBall(ballTexture.getValue());
            this.levelBuilder.setPaddle(paddleTexture.getValue());
            LevelManager.saveLevel(this.levelBuilder.build());
            CheckAlertController.checkLevelCreate();
        }
    }

    /**
     * Clear all the elements on the grid.
     */
    @FXML
    public void clearAll() {
        levelBuilder.deleteAll();
        this.setCanvas();
    }
}
