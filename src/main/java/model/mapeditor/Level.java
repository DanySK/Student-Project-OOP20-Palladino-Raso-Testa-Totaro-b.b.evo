package model.mapeditor;

import java.io.Serializable;
import java.util.Set;

import model.entities.Brick;

/*
 * Provides to create a simple level
 *
 */
public class Level implements Serializable {

    private static final long serialVersionUID = -3269378075735300995L;
    private final Set<Brick> bricks; //deve cambiare brick
    private final String levelName;
    //DA CONTROLLARE, NEL CASO RIMUOVERE private final Music music; 
    private final BackGround background;

    /*
     * Set all required variables 
     * @param bricks
     * @param levelName
     * @param music
     * @param backGround
     */
    public Level(final Set<Brick> bricks, final String levelName, /*final Music music ,*/ final BackGround background) {
        this.bricks = bricks;
        this.levelName = levelName;
        //this.music = music;
        this.background = background;
    }

    /*
     * 
     * @return the amount of bricks in the map
     */
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /*
     * 
     * @return the name of the level
     */
    public String getLevelName() {
        return this.levelName;
    }

    /*
     * DA CONTROLLARE, NEL CASO RIMUOVERE
     * @return the music
     */
    /*
     public Music getMusic() {
         return music;import paranoid.model.entity.Brick;
    }
     */

    /*
     * @return the background
     */
    public BackGround getBackground() {
        return background;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((levelName == null) ? 0 : levelName.hashCode());
        return result;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Level other = (Level) obj;
        if (levelName == null) {
            if (other.levelName != null) {
                return false;
            }
        } else if (!levelName.equals(other.levelName)) {
            return false;
        }
        return true;
    }

}
