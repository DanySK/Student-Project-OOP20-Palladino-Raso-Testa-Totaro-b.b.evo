package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PaddleTexture {
    /**
     * Default theme, paddle.
     */
    PADDLE_DEFAULT("Images/paddle/defaultBall.png ", Theme.DEFAULT),

    /**
     * Arkanoid theme, paddle.
     */
    PADDLE_ARKANOID("Images/paddle/araknoidBall.png ", Theme.ARKANOID),

    /**
     * Galaga theme, paddle.
     */
    PADDLE_GALAGA("Images/paddle/galagaBall.jpg ", Theme.GALAGA),

    /**
     * Pacman theme, ball.
     */
    PADDLE_PACMAN("Images/paddle/pacmanBall.png ", Theme.PACMAN),

    /**
     * Crash Bandicoot theme, paddle.
     */
    PADDLE_CRASH("Images/paddle/crashBall.png ", Theme.CRASH_BANDICOOT),

    /**
     * Super Mario theme, paddle.
     */
    PADDLE_SUPERMARIO("Images/paddle/marioBall.png ", Theme.SUPER_MARIO);

    private String path;
    private Theme theme;

    PaddleTexture(final String path, final Theme theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * @return the path of paddle
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the name of paddle
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * @return the String name of paddle
     */
    public String getStringTheme() {
        return theme.getThemeName();
    }

    /**
     * 
     * @return a list of all available paddle
     */
    public static List<String> getPaddleTexturedNames() {
        return Arrays.asList(PaddleTexture.values()).stream()
                                          .map(i -> i.getStringTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a paddle by name.
     * @param theme to map
     * @return paddle enum
     */
    public static PaddleTexture getPaddleTextureByName(final Theme theme) {
        return Arrays.asList(PaddleTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
