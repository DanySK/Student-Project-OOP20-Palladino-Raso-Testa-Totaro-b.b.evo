package controller.settings;

import controller.sound.SoundController;
import controller.utilities.IOSettings;
import model.settings.GameSettingsBuilder;
import model.settings.GameSettingsBuilderImpl;

public class SettingsControllerImpl implements SettingsController {

    private final GameSettingsBuilder settings;

    public SettingsControllerImpl() {
        this.settings = new GameSettingsBuilderImpl();
        this.settings.enableSoundFx(IOSettings.readSettings().isEnableoundFx());
        this.settings.enableMusic(IOSettings.readSettings().isEnableMusic());
        this.settings.leftAndRight(IOSettings.readSettings().useLeftAndRight());
        this.settings.upAndDown(IOSettings.readSettings().useUpAndDown());
        this.settings.difficulty(IOSettings.readSettings().getDifficulty());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isSoundFxEnable() {
        return IOSettings.readSettings().isEnableoundFx();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isMusicEnable() {
        return IOSettings.readSettings().isEnableMusic();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isLeftAndRightEnable() {
        return IOSettings.readSettings().useLeftAndRight();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isUpAndDownEnable() {
        return IOSettings.readSettings().useUpAndDown();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void changeSoundFxState(final boolean state) {
        this.settings.enableSoundFx(state);
        if (state) {
            SoundController.enableSoundFx();
        } else {
            SoundController.stopFx();
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void changeMusicState(final boolean state) {
        this.settings.enableMusic(state);
        if (state) {
            SoundController.enableMusic();
        } else {
            SoundController.stopMusic();
            SoundController.disableMusic();
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void useLeftAndRightCommand() {
        this.settings.leftAndRight(true);
        this.settings.upAndDown(false);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void useUpAndDownCommand() {
        this.settings.upAndDown(true);
        this.settings.leftAndRight(false);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void saveNewSettings() {
        IOSettings.printInJsonFormat(this.settings.build());
    }

}
