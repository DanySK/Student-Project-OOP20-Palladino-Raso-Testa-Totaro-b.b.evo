package controller.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.sound.SoundController;
import controller.utilities.IOSettings;
import model.settings.GameSettingsBuilder;
import model.settings.GameSettingsBuilderImpl;
import model.utilities.Difficulty;
import view.utilities.PersonalSounds;

public class SettingsControllerImpl implements SettingsController {

    private final GameSettingsBuilder gameSettings;

    public SettingsControllerImpl() {
        this.gameSettings = new GameSettingsBuilderImpl().defaultSettings();
    }

    @Override
    public void changeSoundFxState(final boolean state) {
        if (state) {
            SoundController.enableSoundFx();
        } else {
            SoundController.stopFx();
        }
    }

    @Override
    public void changeMusicState(final boolean state) {
        if (state) {
            SoundController.enableMusic();
            SoundController.playMusic(PersonalSounds.TETRIS_THEME.getURL().getPath());
            this.gameSettings.enableMusic(state);
        } else {
            this.gameSettings.enableMusic(state);
            SoundController.stopMusic();
        }
    }

    @Override
    public void setLeftAndRightMode() {
        this.gameSettings.leftAndRight(true);
        this.gameSettings.upAndDown(false);
    }

    @Override
    public void setUpAndDownMode() {
        this.gameSettings.upAndDown(true);
        this.gameSettings.leftAndRight(false);
    }

    @Override
    public void setDifficulty(final Difficulty difficulty) {
        this.gameSettings.difficulty(difficulty);
    }

    @Override
    public void printSettings() {
        try {
            IOSettings.printInJsonFormat(this.gameSettings.build());
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public void takeSettings() {

    }

}
