package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PowerUpDropTexture {
    /**
     * Default theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_DEFAULT("Images/PowerUpDropTexture/DefaultPowerUpTexture.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_ARKANOID("Images/PowerUpDropTexture/ArkanoidPowerUpTexture.png ", Theme.ARKANOID),

    /**
     * Galaga theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_GALAGA("Images/PowerUpDropTexture/GalagaPowerUpTexture.jpg ", Theme.GALAGA),

    /**
     * Pacman theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_PACMAN("Images/PowerUpDropTexture/BlackPowerUpTexture.png ", Theme.PACMAN),

    /**
     * Donkey Kong theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_DONKEYKONG("Images/PowerUpDropTexture/DonkeyKongPowerUpTexture.jpeg ", Theme.DONKEY_KONG),

    /**
     * Crash Bandicoot theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_CRASH("Images/PowerUpDropTexture/CrashPowerUpTexture.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme, drop powerup texture.
     */
    POWERUP_DTEXTURE_SUPERMARIO("Images/PowerUpDropTexture/SuperMarioPowerUpTexture.png ", Theme.SUPER_MARIO);

    private String path;
    private Theme theme;

    PowerUpDropTexture(final String path, final Theme theme) {
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
    public Theme getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available PowerUpDropTexture
     */
    public static List<Theme> getPowerupDropTextureNames() {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * Return String to indicate the path of the source by theme.
     * @param theme to map
     * @return String path of PowerUpDropTexture
     */
    public static String getPowerUpDropTextureByName(final Theme theme) {
        return Arrays.asList(PowerUpDropTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get()
                                                 .toString();
    }
}
