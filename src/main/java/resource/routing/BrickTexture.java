package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BrickTexture {

    /**
     * Default theme, brick texture.
     */
    BRICK_TEXTURE_DEFAULT("Images/BrickTexture/DefaultBrickTexture.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme, brick texture.
     */
    BRICK_TEXTURE_ARKANOID("Images/BrickTexture/ArkanoidBrickTexture.png ", Theme.ARKANOID),

    /**
     * Galaga theme, brick texture.
     */
    BRICK_TEXTURE_GALAGA("Images/BrickTexture/GalagaBrickTexture.jpg ", Theme.GALAGA),

    /**
     * Pacman theme, brick texture.
     */
    BRICK_TEXTURE_PACMAN("Images/BrickTexture/BlackBrickTexture.png ", Theme.PACMAN),

    /**
     * Donkey Kong theme, brick texture.
     */
    BRICK_TEXTURE_DONKEYKONG("Images/BrickTexture/DonkeyKongBrickTexture.jpeg ", Theme.DONKEY_KONG),

    /**
     * Crash Bandicoot theme, brick texture.
     */
    BRICK_TEXTURE_CRASH("Images/BrickTexture/CrashBrickTexture.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme, brick texture.
     */
    BRICK_TEXTURE_SUPERMARIO("Images/BrickTexture/SuperMarioBrickTexture.png ", Theme.SUPER_MARIO);

    private String path;
    private Theme theme;

    BrickTexture(final String path, final Theme theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * @return the path of brickTexture
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the name of brickTexture
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available brickTexture
     */
    public static List<Theme> getBrickTextureNames() {
        return Arrays.asList(BrickTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * Return String to indicate the path of the source by theme.
     * @param theme to map
     * @return String path of BrickTextureName
     */
    public static String getBrickTextureByName(final Theme theme) {
        return Arrays.asList(BrickTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get()
                                                 .toString();
    }

}
