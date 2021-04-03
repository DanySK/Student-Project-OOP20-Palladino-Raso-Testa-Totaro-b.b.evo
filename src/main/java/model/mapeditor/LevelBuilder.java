package model.mapeditor;

import javafx.scene.paint.Color;
import model.utilities.Pair;

public class LevelBuilder {

    private String levelName;
    private BackGround background;
    //un eventuale music

    public LevelBuilder() {

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
    public Pair<A, Boolean> brickSelected(final double x, final double y, final Color color, final boolean isIndestructibile, 
                                            final int point, final int lives) {

    }

    /**
     * @return level built
     */
    public Level build() {

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

    public void deleteAll() {
        // TODO Auto-generated method stub

    }

}
