package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PowerUpTexture {
    /**
     * Default theme, power up texture.
     */
    POWERUP_TEXTURE_DEFAULT("Images/PowerUpTexture/DefaultPowerUpTexture.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme, power up texture.
     */
    POWERUP_TEXTURE_ARKANOID("Images/PowerUpTexture/ArkanoidPowerUpTexture.png ", Theme.ARKANOID),

    /**
     * Galaga theme, power up texture.
     */
    POWERUP_TEXTURE_GALAGA("Images/PowerUpTexture/GalagaPowerUpTexture.jpg ", Theme.GALAGA),

    /**
     * Pacman theme, power up texture.
     */
    POWERUP_TEXTURE_PACMAN("Images/PowerUpTexture/BlackPowerUpTexture.png ", Theme.PACMAN),

    /**
     * Donkey Kong theme, power up texture.
     */
    POWERUP_TEXTURE_DONKEYKONG("Images/PowerUpTexture/DonkeyKongPowerUpTexture.jpeg ", Theme.DONKEY_KONG),

    /**
     * Crash Bandicoot theme, power up texture.
     */
    POWERUP_TEXTURE_CRASH("Images/PowerUpTexture/CrashPowerUpTexture.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme, power up texture.
     */
    POWERUP_TEXTURE_SUPERMARIO("Images/PowerUpTexture/SuperMarioPowerUpTexture.png ", Theme.SUPER_MARIO);

    private String path;
    private Theme theme;

    PowerUpTexture(final String path, final Theme theme) {
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
    public Theme getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available PowerUpTexture
     */
    public static List<Theme> getPowerupTextureNames() {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * Return String to indicate the path of the source by theme.
     * @param theme to map
     * @return String path of PowerUpTexture
     */
    public static String getPowerUpTextureByName(final Theme theme) {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get()
                                                 .toString();
    }
}
