
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
     * Default theme, background
     */
    BACKGROUND_DEFAULT("Images/background/DefaultBackground.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme, background
     */
    BACKGROUND_ARKANOID("Images/background/ArkanoidBackground.png ", Theme.ARKANOID),

    /**
     * Galaga theme, background
     */
    BACKGROUND_GALAGA("Images/background/GalagaBackground.jpg ", Theme.GALAGA),

    /**
     * Pacman theme, background
     */
    BACKGROUND_PACMAN("Images/background/BlackBackground.png ", Theme.PACMAN),

    /**
     * Donkey Kong theme, background
     */
    BACKGROUND_DONKEYKONG("Images/background/DonkeyKongBackground.jpeg ", Theme.DONKEY_KONG),

    /**
     * Crash Bandicoot theme, background
     */
    BACKGROUND_CRASH("Images/background/CrashBackground1.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme, background
     */
    BACKGROUND_SUPERMARIO("Images/background/SuperMarioBackground.png ", Theme.SUPER_MARIO);

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
     * @param theme to map
     * @return backGround enum
     */
    public static BackGround getBackGroundByName(final Theme theme) {
        return Arrays.asList(BackGround.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }

}
