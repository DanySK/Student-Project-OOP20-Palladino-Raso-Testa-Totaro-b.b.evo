package model.mapeditor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Path of the standard levels.
 *
 */
public enum LevelSelection implements Iterator<LevelSelection> {

    /**
     * Level 1 location and the input to fit the iterator interface.
     */
    LEVEL1("standardLevel/Default", 0, false),

    /**
     * Level 2 location and the input to fit the iterator interface.
     */
    LEVEL2("standardLevel/Arkanoid tribute", 1, false),

    /**
     * Level 3 location and the input to fit the iterator interface.
     */
    LEVEL3("standardLevel/Galaga tribute", 2, false),

    /**
     * Level 4 location and the input to fit the iterator interface.
     */
    LEVEL4("standardLevel/Pacman tribute", 3, false),

    /**
     * Level 5 location and the input to fit the iterator interface.
     */
    LEVEL5("standardLevel/Donkey Kong tribute", 4, false),

    /**
     * Level 6 location and the input to fit the iterator interface.
     */
    LEVEL6("standardLevel/Crash Bandicoot tribute", 5, false),

    /**
     * Level 7 location and the input to fit the iterator interface.
     */
    LEVEL7("standardLevel/Super Mario tribute", 6, false);

    private int index;
    private String path;
    private boolean isLast;

    LevelSelection(final String path, final int index, final boolean isLast) {
        this.path = path;
        this.index = index;
        this.isLast = isLast;
    }

    /**
     * @return index of level
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return path of level
     */
    public String getPath() {
        return path;
    }

    /**
     * @return true if it's the last, false otherwise
     */
    public boolean isLast() {
        return isLast;
    }

    /**
     * 
     * @return load the standard level saved in the resources
     */
    public Level getLevel() {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(ClassLoader.getSystemResourceAsStream(path)))) {
            level = (Level) istream.readObject();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassCastException e3) {
            e3.printStackTrace();
        } 
        return level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return !this.isLast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelSelection next() {
        return Arrays.asList(LevelSelection.values()).get(index + 1);
    }

    /**
     * 
     * @param nameLvl the name level to search
     * @return if the level is one of the default
     */
    public static boolean isStandardLevel(final String nameLvl) {
        return Arrays.asList(LevelSelection.values()).stream()
                                                     .map(i -> i.getLevel().getLevelName())
                                                     .anyMatch(i -> i.equals(nameLvl));
    }

    /**
     * 
     * @param level to search in this enum
     * @return the corresponding enumeration linked to the standard level.
     */
    public static LevelSelection getSelectionFromLevel(final Level level) {
        return Arrays.asList(LevelSelection.values()).stream()
                                                     .filter(i -> i.getLevel().equals(level))
                                                     .findFirst()
                                                     .get();
    }

}
