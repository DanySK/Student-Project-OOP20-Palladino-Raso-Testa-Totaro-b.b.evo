package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Theme {

    /**
     * Default theme.
     */
    DEFAULT("Default"),

    /**
     * Arkanoid theme.
     */
    ARKANOID("Arkanoid"),

    /**
     * Galaga theme.
     */
    GALAGA("Galaga"),

    /**
     * Pacman theme.
     */
    PACMAN("Pacman"),

    /**
     * Donkey Kong theme.
     */
    DONKEY_KONG("DonkeyKong"),

    /**
     * Crash Bandicoot theme.
     */
    CRASH_BANDICOOT("CrashBandicoot"),

    /**
     * Super Mario theme.
     */
    SUPER_MARIO("SuperMario");

    private String themeName;

    Theme(final String themeName) {
        this.themeName = themeName;
    }

    /**
     * @return name of current theme
     */
    public String getThemeName() {
        return this.themeName;
    }

    /**
     * 
     * @return a list of all theme
     */
    public static List<String> getAllThemeNames() {
        return Arrays.asList(Theme.values()).stream()
                                            .map(i -> i.getThemeName())
                                            .collect(Collectors.toList());
    }
}
