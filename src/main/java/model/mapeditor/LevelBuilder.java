package model.mapeditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.entities.Brick;
import model.entities.Brick.Builder;
import model.entities.GameObject;
import model.entities.GameObjectEmpty;
import model.utilities.GameObjStatus;
import model.utilities.GameUtilities;
import model.utilities.Pair;
import model.utilities.Position;
import model.utilities.Texture;
import resource.routing.BackGround;
import resource.routing.PersonalSounds;
import resource.routing.Theme;

public class LevelBuilder {

    //build the map between bricks in the board and coordinates
    private final Map<GameObject, Pair<Integer, Integer>> builderGrid = new HashMap<>();
    //build the map between bricks in the show and in the grid and coordinates
    private final Map<Pair<Integer, Integer>, Pair<GameObject, Optional<Brick>>> gameGrid = new HashMap<>();

    private final int builderBrickDimY = (int) (GameUtilities.CANVAS_HEIGHT / GameUtilities.BRICK_NUMBER_Y);
    private final int builderBrickDimX = (int) (GameUtilities.CANVAS_WIDTH / GameUtilities.BRICK_NUMBER_X);
    private final int gameBrickDimY = (int) (GameUtilities.WORLD_HEIGHT / GameUtilities.BRICK_NUMBER_Y);
    private final int gameBrickDimX = (int) (GameUtilities.WORLD_WIDTH / GameUtilities.BRICK_NUMBER_X);

    private String levelName;
    private BackGround background;
    private PersonalSounds music;

    public LevelBuilder() {
        int currentXpos = 0;
        for (int i = 0; i < GameUtilities.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < GameUtilities.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                //Ho lasciato gameobject ma forse ci va gameobjectEmpty da riguardare forse, nel caso anche nelle map da sistemare
                final GameObject objectEmpty = new GameObjectEmpty(new Position(currentXpos, currentYpos), builderBrickDimY, builderBrickDimX);
                this.builderGrid.put(objectEmpty, coordinates);
                currentYpos += builderBrickDimY;
            }
            currentXpos += builderBrickDimX;
        }
        currentXpos = 0;
        for (int i = 0; i < GameUtilities.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < GameUtilities.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final GameObject objectEmpty = new GameObjectEmpty(new Position(currentXpos, currentYpos), builderBrickDimY, builderBrickDimX);
                this.gameGrid.put(coordinates, new Pair<>(objectEmpty, Optional.empty()));
                currentYpos += gameBrickDimY;
            }
            currentXpos += gameBrickDimX;
        }
    }

    /**
     * First compare the x, y coordinates of the click with the grid containing, then
     * the game object built on the dimensions of the current size of the screen 
     * by returning the number of the brick selected.
     * Check in the grid containing the game object built on the size of the board which coordinate was selected.
     * Checks if the paddle has already selected that brick 
     * if he had not selected I call the brick builder and build the brick with the form inputs 
     * and the dimensions of the GameObjectEmpty built on the size of the board
     * @param x mouse coordinates mouse x coordinate
     * @param y mouse coordinates mouse y coordinate
     * @param texture texture selected
     * @param state check if is destroyable or not
     * @param durability lives remaining before destroy the brick
     * @return current game grid state
     */
    public Pair<GameObject, Boolean> brickSelected(final double x, final double y, 
                                                    final Texture texture, final GameObjStatus state, final int durability) {
        Pair<GameObject, Boolean> retState = new Pair<>(new GameObjectEmpty(new Position(0, 0), 0, 0), false);
        for (final GameObject objectEmpty : this.builderGrid.keySet()) {
            if (x > objectEmpty.getPos().getX() && x < objectEmpty.getPos().getX() + objectEmpty.getWidth() && y > objectEmpty.getPos().getY()
                    && y < objectEmpty.getPos().getY() + objectEmpty.getHeight()) {
                final Pair<Integer, Integer> brickSelected = this.builderGrid.get(objectEmpty);
                if (this.gameGrid.get(brickSelected).getY().isPresent()) {
                    this.gameGrid.replace(brickSelected, new Pair<>(this.gameGrid.get(brickSelected).getX(), Optional.empty()));
                    retState = new Pair<>(objectEmpty, false);
                } else {
                    final Builder brickBuilder = new Builder();
                    final GameObject gameObjectEmpty = this.gameGrid.get(brickSelected).getX();
                    final Brick brick = brickBuilder.setPos(new Position(gameObjectEmpty.getPos().getX(), gameObjectEmpty.getPos().getY()))
                                               .setHeight(this.gameGrid.get(brickSelected).getX().getHeight())
                                               .setWidth(this.gameGrid.get(brickSelected).getX().getWidth())
                                               .setStatus(state)
                                               .setTexture(texture.buildTexturePath())
                                               .setDurability(durability)
                                               .build();
                    this.gameGrid.replace(brickSelected, new Pair<>(this.gameGrid.get(brickSelected).getX(), Optional.of(brick)));
                    retState = new Pair<>(objectEmpty, true);
                }
            }
        }
        return retState;
    }

    /**
     * @return level built
     */
    public Level build() {
        final Set<Brick> levelBrick = this.gameGrid.entrySet().stream()
                .map(i -> i.getValue().getY()).filter(i -> i.isPresent()).map(i -> i.get()).collect(Collectors.toSet());
        return new Level(levelBrick, levelName, music, background);
    }

    /**
     * @param levelName
     */
    public void setLevelName(final String levelName) {
        this.levelName = levelName;
    }

    /**
     * @param backGround to set
     */
    public void setBackGround(final Theme backGround) {
        this.background = BackGround.getBackGroundByName(backGround);
    }

    /**
     * @param music to set
     */
    public void setMusic(final String music) {
        this.music = PersonalSounds.getSoundsByName(music);
    }

    /**
     * Delete all elements. 
     */
    public void deleteAll() {
        for (final var elem : this.gameGrid.keySet()) {
            this.gameGrid.replace(elem, new Pair<>(this.gameGrid.get(elem).getX(), Optional.empty()));
        }
    }

}
