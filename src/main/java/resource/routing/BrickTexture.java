package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BrickTexture {

    /**
     * Default theme, brick texture.
     */
    BRICK_TEXTURE_DEFAULT("Images/BrickTexture/DefaultBrickTexture.png ", "Default"),

    /**
     * Arkanoid theme, brick texture.
     */
    BRICK_TEXTURE_ARKANOID("Images/BrickTexture/ArkanoidBrickTexture.png ", "Arkanoid"),

    /**
     * Galaga theme, brick texture.
     */
    BRICK_TEXTURE_GALAGA("Images/BrickTexture/GalagaBrickTexture.jpg ", "Galaga"),

    /**
     * Pacman theme, brick texture.
     */
    BRICK_TEXTURE_PACMAN("Images/BrickTexture/PacmanBrickTexture.png ", "Pacman"),
    /**
     * Crash Bandicoot theme, brick texture.
     */
    BRICK_TEXTURE_CRASH("Images/BrickTexture/CrashBrickTexture.png ", "Crash"),

    /**
     * Super Mario theme, brick texture.
     */
    BRICK_TEXTURE_SUPERMARIO("Images/BrickTexture/SuperMarioBrickTexture.png ", "SuperMario");

    private String path;
    private String theme;

    BrickTexture(final String path, final String theme) {
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
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available brickTexture
     */
    public static List<String> getBrickTextureNames() {
        return Arrays.asList(BrickTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a brick by name.
     * @param theme to map
     * @return String path of BrickTextureName
     */
    public static String getBrickTextureByName(final String theme) {
        return Arrays.asList(BrickTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get()
                                                 .toString();
    }

}
