package model.mapeditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import model.entities.Brick;
import model.entities.GameObject;
import model.utilities.GameUtilities;
import model.utilities.Pair;
import model.utilities.Position;
import paranoid.model.entity.PlaceHolder;
import paranoid.model.entity.Brick.Builder;
import resource.routing.PersonalSounds;

public class LevelBuilder {

    //build the map between bricks in the board and coordinates
    private final Map<GameObject, Pair<Integer, Integer>> builderGrid = new HashMap<>();
    //build the map between bricks in the show and in the grid and coordinates DA SISTEMARE IL COMMENTO PERCHE NON SI CAPISCE BENISSIMO
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
                final GameObject ph = new GameObject(new Position(currentXpos, currentYpos), builderBrickDimY, builderBrickDimX);
                this.builderGrid.put(ph, coordinates);
                currentYpos += builderBrickDimY;
            }
            currentXpos += builderBrickDimX;
        }
        currentXpos = 0;
        for (int i = 0; i < GameUtilities.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < GameUtilities.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final GameObject ph = new GameObject(new Position(currentXpos, currentYpos), gameBrickDimY, gameBrickDimX);
                this.gameGrid.put(coordinates, new Pair<>(ph, Optional.empty()));
                currentYpos += gameBrickDimY;
            }
            currentXpos += gameBrickDimX;
        }
    }

    /**
     * DA RIGUARDARE IN GENERALE IL COMMENTO, SISTEMARE IL BUILDER, SISTEMARE I NOMI PROBABILMENTE E AGGIUNGERE TEXTURE E VEDERE COME FARE
     * PER POINTS E VITE
     * First compare the x, y coordinates of the click with the grid containing, then
     * the game object built on the dimensions of the current size of the screen 
     * by returning the number of the brick hit.
     * Check in the grid containing the game object built on the size of the world 
     * which coordinate was hit. 
     * Checks if the paddle has already selected that brick 
     * if he had not selected I call the brick builder and build the brick with the form inputs 
     * and the dimensions of the placeholder built on the size of the world 
     * @param x mouse coordinates mouse x coordinate
     * @param y mouse coordinates mouse y coordinate
     * @param color color selected
     * @param isIndestructible if the brick is indestructible
     * @param point point earned
     * @param lives lives remaining
     * @return current game grid state
     */
    public Pair<GameObject, Boolean> brickSelected(final double x, final double y, 
                                                    final Color color, final boolean isIndestructible, 
                                                    final int point, final int lives) { //PUNTI E VITE SARANNO DA MODIFICARE PROBABILMENTE
        Pair<GameObject, Boolean> res = new Pair<>(new GameObject(new Position(0, 0), 0, 0), false);
        for (final GameObject ph : this.builderGrid.keySet()) {
            if (x > ph.getPos().getX() && x < ph.getPos().getX() + ph.getWidth() && y > ph.getPos().getY()
                    && y < ph.getPos().getY() + ph.getHeight()) {
                final Pair<Integer, Integer> hit = this.builderGrid.get(ph);
                if (this.gameGrid.get(hit).getY().isPresent()) {
                    this.gameGrid.replace(hit, new Pair<>(this.gameGrid.get(hit).getX(), Optional.empty()));
                    res = new Pair<>(ph, false);
                } else {
                    final Builder builder = new Builder(); //BUILDER DEL BRICK, DEVE IMPLEMENTARLO LUI
                    final GameObject gamePlaceHolder = this.gameGrid.get(hit).getX();
                    final Brick brick = builder.position(new Position(gamePlaceHolder.getPos().getX(), gamePlaceHolder.getPos().getY()))
                                               .height(this.gameGrid.get(hit).getX().getHeight())
                                               .width(this.gameGrid.get(hit).getX().getWidth())
                                               .pointEarned(point)
                                               .color(color)
                                               .indestructible(isIndestructible)
                                               .energy(lives)
                                               .build();
                    this.gameGrid.replace(hit, new Pair<>(this.gameGrid.get(hit).getX(), Optional.of(brick)));
                    res = new Pair<>(ph, true);
                }
            }
        }
        return res;
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
