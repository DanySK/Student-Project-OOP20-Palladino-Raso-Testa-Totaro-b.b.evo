
package resource.routing;

import java.io.InputStream;
import java.net.URL;

public enum PersonalImages {

    /**
     * Path for Ranking Image.
     */
    RANKING_IMG("Images/mainMenuImg/video.png"),

    /**
     * Path for Cursor Image.
     */
    GLOW_POINTER("Images/cursor/glowPointer.png"),

    /**
     * Path for Next icon.
     */
    NEXT_IMG("Images/character/next.png"),

    /**
     * Path for Game Icon Cursor Image.
     */
    GAME_ICON_IMG("Images/icon/GameIcon.png"),

    /**
     * Path for the default Video Tutorial.
     */
    TUTORIAL_DEFAULT("Images/video/prova.gif"),

    /**
     * Path for Main menu Video Tutorial.
     */
    TUTORIAL_MAIN_MENU("Images/video/prova.gif"),

    /**
     * Path for HowToPlay Video Tutorial.
     */
    TUTORIAL_HOW_TO_PLAY("Images/video/prova.gif"),

    /**
     * Path for Settings Video Tutorial.
     */
    TUTORIAL_SETTINGS("Images/video/prova.gif"),

    /**
     * Path for GameOver view background.
     */
    GAMEOVER_BACKGROUND_IMG("Images/background/GameOverBackground.png");

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
