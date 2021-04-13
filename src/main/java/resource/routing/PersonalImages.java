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
     * Path for Game Video Tutorial.
     */
    TUTORIAL_VIDEO("Images/video/Tutorial.mp4"),

    /**
     * Path for Ball Image.
     */
    BALL_IMG("Images/ball/defaultBall.png"),

    /**
     * Path for Paddle Image.
     */
    PADDLE_IMG("Images/paddle/redPaddle.png");


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
