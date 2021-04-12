package model.utilities;

import resource.routing.Theme;

public class Texture {
    /**
     * Folder for Brick texture.
     */
    public static final String BRICK_TEXTURE = "Images/background/";
    /**
     * Folder for PowerUp brick texture.
     */
    public static final String BRICK_POWERUP_TEXTURE = "Images/background/";
    /**
     * Folder for PowerUp texture.
     */
    public static final String POWERUP_TEXTURE = "Images/background/";

    /**
     * Format of file.
     */
    public static final String FILE_FORMAT = ".png";

    private final String path;
    private final Theme currentTheme;

    /**
     * 
     * @param path
     * @param theme
     */
    public Texture(final String path, final Theme theme) {
        this.path = path;
        this.currentTheme = theme;
    }

    /**
     * 
     * @return texture path 
     */
    public String buildTexturePath() {
        return path + currentTheme.getThemeName() + FILE_FORMAT;
    }
}
