package model.utilities;

import resource.routing.Theme;

public class Texture {
    /**
     * 
     */
    public static final String BRICK_TEXTURE = "Images/background/";
    public static final String BRICK_POWERUP_TEXTURE = "Images/background/";
    public static final String POWERUP_TEXTURE = "Images/background/";

    private String path;
    private Theme currentTheme;
    private String fileFormat = ".png";

    /**
     * 
     * @param path
     * @param theme
     * @param fileFormat
     */
    public Texture(final String path, Theme theme, String fileFormat) {
        this.path = path;
        this.currentTheme = theme;
        this.fileFormat = fileFormat;
    }

    /**
     * 
     * @return texture path 
     */
    public String buildTexturePath() {
        return path + currentTheme.getThemeName() + fileFormat;
    }
}
