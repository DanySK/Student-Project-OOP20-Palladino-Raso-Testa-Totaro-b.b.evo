package model.settings;

import model.utilities.Difficulty;

public interface GameSettingsBuilder {

    /**
     * Used to set the soundFx.
     * @param isEnableSoundFX
     * @return the soundFx property.
     */
    GameSettingsBuilder enableSoundFx(boolean isEnableSoundFX);

    /**
     * Used to set the Music.
     * @param isEnableMusic
     * @return the Music property.
     */
    GameSettingsBuilder enableMusic(boolean isEnableMusic);

    /**
     * Used to set the left and right game movement.
     * @param useLeftAndRight
     * @return the left and right game movement property.
     */
    GameSettingsBuilder leftAndRight(boolean useLeftAndRight);
    /**
     * Used to set the up and down game movement.
     * @param useUpAndDown
     * @return the up and down game movement property.
     */
    GameSettingsBuilder upAndDown(boolean useUpAndDown);

    /**
     * Used to set the difficulty of the game.
     * @param difficulty
     * @return the difficulty property.
     */
    GameSettingsBuilder difficulty(Difficulty difficulty);

    /**
     * Used to build a correct version of Game Settings.
     * @return a correct version of Game Settings whit validation data.
     */
    Settings build();
}
