package resource.routing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PaddleTexture {
    /**
     * Default theme, paddle.
     */
    PADDLE_DEFAULT("Images/paddle/defaultBall.png ", "Default"),

    /**
     * Arkanoid theme, paddle.
     */
    PADDLE_ARKANOID("Images/paddle/araknoidBall.png ", "Arkanoid"),

    /**
     * Galaga theme, paddle.
     */
    PADDLE_GALAGA("Images/paddle/galagaBall.jpg ", "Galaga"),

    /**
     * Pacman theme, ball.
     */
    PADDLE_PACMAN("Images/paddle/pacmanBall.png ", "Pacman"),

    /**
     * Crash Bandicoot theme, paddle.
     */
    PADDLE_CRASH("Images/paddle/crashBall.png ", "Crash"),

    /**
     * Super Mario theme, paddle.
     */
    PADDLE_SUPERMARIO("Images/paddle/marioBall.png ", "SuperMario");

    private String path;
    private String theme;

    PaddleTexture(final String path, final String theme) {
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
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available paddle
     */
    public static List<String> getPaddleTexturedNames() {
        return Arrays.asList(PaddleTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a paddle by name.
     * @param theme to map
     * @return paddle enum
     */
    public static PaddleTexture getPaddleTextureByName(final String theme) {
        return Arrays.asList(PaddleTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
