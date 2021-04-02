package controller.settings;

import model.utilities.Difficulty;

public interface SettingsController {

    void changeSoundFxState(boolean state);

    void changeMusicState(boolean state);

    void setLeftAndRightMode();

    void setUpAndDownMode();

    void setDifficulty(Difficulty difficulty);

    void printSettings();

    void takeSettings();
}
