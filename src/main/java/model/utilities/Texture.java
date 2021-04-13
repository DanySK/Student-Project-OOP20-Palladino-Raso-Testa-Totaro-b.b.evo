package model.utilities;

import resource.routing.BrickTexture;
import resource.routing.PowerUpDropTexture;
import resource.routing.PowerUpTexture;
import resource.routing.Theme;

public class Texture {

    private final String theme;

    /**
     * 
     * @param theme
     */
    public Texture(final String theme) {
        this.theme = theme;
    }

    /**
     * @return Path of BrickTexture
     */
    public String buildBrickTexturePath() {
        return BrickTexture.getBrickTextureByName(theme);
    }

    /**
     * @return Path of PowerUpTexture
     */
    public String buildPowerUpTexturePath() {
        return PowerUpTexture.getPowerUpTextureByName(theme);
    }

    /**
     * @return Path of PowerUpDropTexture
     */
    public String buildPowerUpDropTexturePath() {
        return PowerUpDropTexture.getPowerUpDropTextureByName(theme);
    }

}
