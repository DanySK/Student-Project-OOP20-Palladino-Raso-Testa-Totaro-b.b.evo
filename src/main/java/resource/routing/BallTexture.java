package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BallTexture {

    /**
     * Default theme, ball.
     */
    BALL_DEFAULT("Images/ball/defaultBall.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme, ball.
     */
    BALL_ARKANOID("Images/ball/araknoidBall.png ", Theme.ARKANOID),

    /**
     * Galaga theme, ball.
     */
    BALL_GALAGA("Images/ball/galagaBall.jpg ", Theme.GALAGA),

    /**
     * Pacman theme, ball.
     */
    BALL_PACMAN("Images/ball/pacmanBall.png ", Theme.PACMAN),

    /**
     * Crash Bandicoot theme, ball.
     */
    BALL_CRASH("Images/ball/crashBall.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme, ball.
     */
    BALL_SUPERMARIO("Images/ball/marioBall.png ", Theme.SUPER_MARIO);

    private String path;
    private Theme theme;

    BallTexture(final String path, final Theme theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * @return the path of ball
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the name of ball
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * @return the String name of ball
     */
    public String getStringTheme() {
        return theme.getThemeName();
    }

    /**
     * 
     * @return a list of all available ball
     */
    public static List<String> getBallTexturedNames() {
        return Arrays.asList(BallTexture.values()).stream()
                                          .map(i -> i.getStringTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a ball by name.
     * @param theme to map
     * @return ball enum
     */
    public static BallTexture getBallTextureByName(final Theme theme) {
        return Arrays.asList(BallTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
