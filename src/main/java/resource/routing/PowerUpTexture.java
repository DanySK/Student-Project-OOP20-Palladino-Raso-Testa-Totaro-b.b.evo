package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PowerUpTexture {
    /**
     * Default theme, power up texture.
     */
    POWERUP_TEXTURE_DEFAULT("Images/PowerUpTexture/DefaultPowerUpTexture.png ", "Default"),

    /**
     * Arkanoid theme, power up texture.
     */
    POWERUP_TEXTURE_ARKANOID("Images/PowerUpTexture/ArkanoidPowerUpTexture.png ", "Arkanoid"),

    /**
     * Galaga theme, power up texture.
     */
    POWERUP_TEXTURE_GALAGA("Images/PowerUpTexture/GalagaPowerUpTexture.jpg ", "Galaga"),

    /**
     * Pacman theme, power up texture.
     */
    POWERUP_TEXTURE_PACMAN("Images/PowerUpTexture/BlackPowerUpTexture.png ", "Pacman"),

    /**
     * Crash Bandicoot theme, power up texture.
     */
    POWERUP_TEXTURE_CRASH("Images/PowerUpTexture/CrashPowerUpTexture.png ", "Crash"),

    /**
     * Super Mario theme, power up texture.
     */
    POWERUP_TEXTURE_SUPERMARIO("Images/PowerUpTexture/SuperMarioPowerUpTexture.png ", "SuperMario");

    private String path;
    private String theme;

    PowerUpTexture(final String path, final String theme) {

        this.path = path;
        this.theme = theme;
    }

    /**
     * @return the path of PowerUpTexture
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the name of PowerUpTexture
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available PowerUpTexture
     */
    public static List<String> getPowerupTextureNames() {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a powerup by name.
     * @param theme to map
     * @return String path of PowerUpTexture
     */
    public static String getPowerUpTextureByName(final String theme) {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get()
                                                 .toString();
    }
}
