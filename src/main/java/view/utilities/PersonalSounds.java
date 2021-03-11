package view.utilities;

public enum PersonalSounds {

    /***
     * Sound generate by click button event.
     * */
    TICK_BUTTON("/Sounds/SoundFx/SoundClick.wav"),

    /***
     * Sound generate by click event of CheckBox and Radio Button.
     * */
    TICK_SPECIALBUTTON("/Sounds/SoundFx/SoundSpecialButton.wav"),

    /***
     * Main Menu Theme.
     * */
    TETRIS_THEME("/Sounds/Music/KorbenikiAlexTesta(GameVersion).wav");

    private String path;

    PersonalSounds(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
