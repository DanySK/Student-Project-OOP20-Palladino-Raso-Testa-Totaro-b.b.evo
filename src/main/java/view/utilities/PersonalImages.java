package view.utilities;

import java.io.InputStream;
import java.net.URL;

public enum PersonalImages {


    /**
     * Path for Play Image.
     */
    PLAY_IMG("Images/mainMenuImg/btnPlay.png"),

    /**
     * Path for Settings Image.
     */
    SETTINGS_IMG("Images/mainMenuImg/settings.png"),

    /**
     * Path for Tutorial Image.
     */
    TUTORIAL_IMG("Images/mainMenuImg/credit-card.png"),

    /**
     * Path for Ranking Image.
     */
    RANKING_IMG("Images/mainMenuImg/video.png"),

    /**
     * Path for Back Image.
     */
    BACK_IMG("Images/settingsImg/Back.png"),

    /**
     * Path for Pacman Cursor Image.
     */
    CURSOR_PACMAN_IMG("Images/cursor/pacmanCursor.png"),

    /**
     * Path for Next icon.
     */
    NEXT_IMG("Images/character/next.png"),

    /**
     * Path for Game Icon Cursor Image.
     */
    GAME_ICON_IMG("Images/icon/GameIcon.png"),
 
    /**
     * Path for Game Video Tutorial.
     */
    TUTORIAL_VIDEO("Images/video/Tutorial.mp4");

    private String path;

    PersonalImages(final String path) {
        this.path = path;
    }

    public URL getURL() {
        return ClassLoader.getSystemResource(this.path);
    }

    public InputStream getResourceAsStream() {
        return ClassLoader.getSystemResourceAsStream(this.path);
    }
}
