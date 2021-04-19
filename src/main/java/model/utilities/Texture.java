package model.utilities;

import resource.routing.BrickTexture;
import resource.routing.PowerUpDropTexture;
import resource.routing.PowerUpTexture;

/**
 * Build the path of texture elements.
 *
 */
public class Texture {

    private final String theme;

    /**
     * Constructor for texture class.
     * @param theme name of current theme
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
     * @return Path of Undestructible BrickTexture
     */
    public String buildUnderBrickTexturePath() {
        return BrickTexture.BRICK_TEXTURE_UNDESTRUCTIBLE.getPath();
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
        return PowerUpDropTexture.getPowerUpDropTextureByName(theme).getPath();
    }

}
