package model.mapeditor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Background resource
 */
public enum Background implements Serializable {

    /*
     * Default theme
     */
    BACKGROUND_1(" ", "Standard"),

    /*
     * Arkanoid theme
     */
    BACKGROUND_2(" ", "Arkanoid"),

    /*
     * Pacman theme
     */
    BACKGROUND_3(" ", "Pacman"),

    /*
     * Super Mario theme
     */
    BACKGROUND_4(" ", "Super Mario"),

    /*
     * Donkey Kong theme
     */
    BACKGROUND_5(" ", "Donkey Kong"),

    /*
     * Crash Bandicoot theme
     */
    BACKGROUND_6(" ", "Crash Bandicoot"),

    /*
     * Galaga theme
     */
    BACKGROUND_7(" ", "Galaga");

    private String path;
    private String name;

    Background(final String path, final String name) {
        this.path = path;
        this.name = name;
    }

    /**
     * @return the path of background
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the name of background
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return a list of all available backgrounds
     */
    public static List<String> getBackgroundNames() {
        return Arrays.asList(Background.values()).stream()
                                          .map(i -> i.getName())
                                          .collect(Collectors.toList());
    }

    /**
     * the reference to the enumeration of a background by name.
     * @param name to map
     * @return background enum
     */
    public static Background getBackGroundByName(final String name) {
        return Arrays.asList(Background.values()).stream()
                                                 .filter(i -> i.getName().equals(name))
                                                 .findFirst()
                                                 .get();
    }

}
