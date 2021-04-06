package model.mapeditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import model.entities.Brick;
import model.entities.GameObject;
import model.utilities.ConstantScreen;
import model.utilities.Pair;
import model.utilities.Position;
import view.utilities.PersonalSounds;

public class LevelBuilder {

    //build the map between bricks in the world and coordinates
    private final Map<GameObject, Pair<Integer, Integer>> builderGrid = new HashMap<>();
    //build the map between bricks in the show in the canvas and coordinates
    private final Map<Pair<Integer, Integer>, Pair<GameObject, Optional<Brick>>> gameGrid = new HashMap<>();
    private final int builderBrickDimY = (int) (ConstantScreen.CANVAS_HEIGHT / ConstantScreen.BRICK_NUMBER_Y);
    private final int builderBrickDimX = (int) (ConstantScreen.CANVAS_WIDTH / ConstantScreen.BRICK_NUMBER_X);
    private final int gameBrickDimY = (int) (ConstantScreen.WORLD_HEIGHT / ConstantScreen.BRICK_NUMBER_Y);
    private final int gameBrickDimX = (int) (ConstantScreen.WORLD_WIDTH / ConstantScreen.BRICK_NUMBER_X);

    private String levelName;
    private BackGround background;
    private PersonalSounds music;

    public LevelBuilder() {
        int currentXpos = 0;
        for (int i = 0; i < ConstantScreen.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < ConstantScreen.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final GameObject ph = new GameObject(new Position(currentXpos, currentYpos), builderBrickDimY, builderBrickDimX);
                this.builderGrid.put(ph, coordinates);
                currentYpos += builderBrickDimY;
            }
            currentXpos += builderBrickDimX;
        }
        currentXpos = 0;
        for (int i = 0; i < ConstantScreen.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < ConstantScreen.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final GameObject ph = new GameObject(new Position(currentXpos, currentYpos), gameBrickDimY, gameBrickDimX);
                this.gameGrid.put(coordinates, new Pair<>(ph, Optional.empty()));
                currentYpos += gameBrickDimY;
            }
            currentXpos += gameBrickDimX;
        }
    }

    /**
     * DA VEDERE COME AGGIUNGERE LA TEXTURE.
     * @param x
     * @param y
     * @param color
     * @param isIndestructibile
     * @param point
     * @param lives
     * @return coordinates
     */
    public Pair<GameObject, Boolean> brickSelected(final double x, final double y, final Color color, final boolean isIndestructibile, 
                                            final int point, final int lives) {

    }

    /**
     * @return level built
     */
    public Level build() {
        final Set<Brick> level = this.gameGrid.entrySet().stream()
                .map(i -> i.getValue().getY()).filter(i -> i.isPresent()).map(i -> i.get()).collect(Collectors.toSet());
        return new Level(level, levelName, music, background);
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
    public void setBackGround(final String backGround) {
        this.background = BackGround.getBackGroundByName(backGround);
    }

    /**
     * @param music to set
     */
    public void setSong(final String music) {
        this.music = PersonalSounds.getMusicByName(music);
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
