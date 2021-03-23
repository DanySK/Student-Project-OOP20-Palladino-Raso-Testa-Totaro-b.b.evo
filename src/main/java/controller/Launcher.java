package controller;
import com.sun.javafx.application.LauncherImpl;

/**
 * Start the application.
 *
 */
public final class Launcher {

    /**
     * Empty constructor.
     */
    private Launcher() {

    }

    /**
     * Main method.
     * @param args
     */
    public static void main(final String[] args) {
        LauncherImpl.launchApplication(Main.class, args);
    }
}
