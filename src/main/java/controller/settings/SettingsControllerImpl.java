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

    @Override
    public boolean isSoundFxEnable() {
        return IOSettings.readSettings().isEnableoundFx();
    }

    @Override
    public boolean isMusicEnable() {
        return IOSettings.readSettings().isEnableMusic();
    }

    @Override
    public boolean isLeftAndRightEnable() {
        return IOSettings.readSettings().useLeftAndRight();
    }

    @Override
    public boolean isUpAndDownEnable() {
        return IOSettings.readSettings().useUpAndDown();
    }

    @Override
    public void changeSoundFxState(final boolean state) {
        this.settings.enableSoundFx(state);
        if (state) {
            SoundController.enableSoundFx();
        } else {
            SoundController.stopFx();
        }
    }

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

    @Override
    public void changeGameController() {
        if (IOSettings.readSettings().useLeftAndRight()) {
            this.settings.upAndDown(true);
            this.settings.leftAndRight(false);
        } else {
            this.settings.leftAndRight(true);
            this.settings.upAndDown(false);
        }
    }

    @Override
    public void saveNewSettings() {
        IOSettings.printInJsonFormat(this.settings.build());
    }

}
