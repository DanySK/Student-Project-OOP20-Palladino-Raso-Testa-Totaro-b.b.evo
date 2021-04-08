package model.mapeditor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Background resource.
 */
public enum BackGround implements Serializable {

    /**
     * Default theme.
     */
    BACKGROUND_1("Images/background/DefaultBackground.png ", "Default"),

    /**
     * Arkanoid theme.
     */
    BACKGROUND_2("Images/background/ArkanoidBackground.png ", "Arkanoid"),

    /**
     * Galaga theme.
     */
    BACKGROUND_3("Images/background/GalagaBackground.jpg ", "Galaga"),

    /**
     * Pacman theme.
     */
    BACKGROUND_4("Images/background/BlackBackground.png ", "Pacman"),

    /**
     * Donkey Kong theme.
     */
    BACKGROUND_5("Images/background/DonkeyKongBackground.jpeg ", "Donkey Kong"),

    /**
     * Crash Bandicoot theme.
     */
    BACKGROUND_6("Images/background/CrashBackground1.png ", "Crash Bandicoot"),

    /**
     * Super Mario theme.
     */
    BACKGROUND_7("Images/background/SuperMarioBackground.png ", "Super Mario");

    private String path;
    private String name;

    BackGround(final String path, final String name) {
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
    public static List<String> getBackGroundNames() {
        return Arrays.asList(BackGround.values()).stream()
                                          .map(i -> i.getName())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a background by name.
     * @param name to map
     * @return backGround enum
     */
    public static BackGround getBackGroundByName(final String name) {
        return Arrays.asList(BackGround.values()).stream()
                                                 .filter(i -> i.getName().equals(name))
                                                 .findFirst()
                                                 .get();
    }

}
