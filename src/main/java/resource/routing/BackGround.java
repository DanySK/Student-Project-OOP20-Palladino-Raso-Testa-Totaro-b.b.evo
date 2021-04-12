
package resource.routing;

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
    BACKGROUND_1("Images/background/DefaultBackground.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme.
     */
    BACKGROUND_2("Images/background/ArkanoidBackground.png ", Theme.ARKANOID),

    /**
     * Galaga theme.
     */
    BACKGROUND_3("Images/background/GalagaBackground.jpg ", Theme.GALAGA),

    /**
     * Pacman theme.
     */
    BACKGROUND_4("Images/background/BlackBackground.png ", Theme.PACMAN),

    /**
     * Donkey Kong theme.
     */
    BACKGROUND_5("Images/background/DonkeyKongBackground.jpeg ", Theme.DONKEY_KONG),

    /**
     * Crash Bandicoot theme.
     */
    BACKGROUND_6("Images/background/CrashBackground1.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme.
     */
    BACKGROUND_7("Images/background/SuperMarioBackground.png ", Theme.SUPER_MARIO);

    private String path;
    private Theme theme;

    BackGround(final String path, final Theme theme) {
        this.path = path;
        this.theme = theme;
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
    public Theme getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available backgrounds
     */
    public static List<Theme> getBackGroundNames() {
        return Arrays.asList(BackGround.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a background by name.
     * @param name to map
     * @return backGround enum
     */
    public static BackGround getBackGroundByName(final String name) {
        return Arrays.asList(BackGround.values()).stream()
                                                 .filter(i -> i.getTheme().equals(name))
                                                 .findFirst()
                                                 .get();
    }

}
