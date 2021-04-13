package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PowerUpDropTexture {
    /**
     * Default theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_DEFAULT("Images/PowerUpDropTexture/DefaultPowerUpTexture.png ", "Default"),

    /**
     * Arkanoid theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_ARKANOID("Images/PowerUpDropTexture/ArkanoidPowerUpTexture.png ", "Arkanoid"),

    /**
     * Galaga theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_GALAGA("Images/PowerUpDropTexture/GalagaPowerUpTexture.jpg ", "Galaga"),

    /**
     * Pacman theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_PACMAN("Images/PowerUpDropTexture/BlackPowerUpTexture.png ", "Pacman"),
    /**
     * Crash Bandicoot theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_CRASH("Images/PowerUpDropTexture/CrashPowerUpTexture.png ", "Crash"),

    /**
     * Super Mario theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_SUPERMARIO("Images/PowerUpDropTexture/SuperMarioPowerUpTexture.png ", "SuperMario");

    private String path;
    private String theme;

    PowerUpDropTexture(final String path, final String theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * @return the path of PowerUpDropTexture
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the name of PowerUpDropTexture
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available PowerUpDropTexture
     */
    public static List<String> getPowerupDropTextureNames() {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a dropped powerup by name.
     * @param theme to map
     * @return String path of PowerUpDropTexture
     */
    public static String getPowerUpDropTextureByName(final String theme) {
        return Arrays.asList(PowerUpDropTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get()
                                                 .toString();
    }
}
