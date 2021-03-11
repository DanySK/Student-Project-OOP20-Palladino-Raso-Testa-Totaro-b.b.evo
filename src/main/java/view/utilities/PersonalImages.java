package view.utilities;

public enum PersonalImages {


    /**
     * Path for Play Image.
     */
    PLAY_IMG("/Images/MainMenuImg/BtnPlay.png"),

    /**
     * Path for Settings Image.
     */
    SETTINGS_IMG("/Images/MainMenuImg/Settings.png"),

    /**
     * Path for Tutorial Image.
     */
    TUTORIAL_IMG("/Images/MainMenuImg/Credit-card.png"),

    /**
     * Path for Ranking Image.
     */
    RANKING_IMG("/Images/MainMenuImg/Video.png"),

    /**
     * Path for Back Image.
     */
    BACK_IMG("/Images/settingsImg/Back.png"),

    /**
     * Path for Pacman Cursor Image.
     */
    CURSOR_PACMAN_IMG("/Images/Cursor/PacmanCursor.png"),

    /**
     * Path for Game Icon Cursor Image.
     */
    GAME_ICON_IMG("/Images/icon/GameIcon.png"),
 
    /**
     * Path for Game Video Tutorial.
     */
    TUTORIAL_VIDEO("/Images/video/Tutorial.mp4");

    private String path;

    PersonalImages(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
