package controller.settings;

public interface SettingsController {

    boolean isSoundFxEnable();

    boolean isMusicEnable();

    boolean isLeftAndRightEnable();

    boolean isUpAndDownEnable();

    void changeSoundFxState(boolean state);

    void changeMusicState(boolean state);
    
    void useLeftAndRightCommand();
    
    void useUpAndDownCommand();

    void saveNewSettings();
}
