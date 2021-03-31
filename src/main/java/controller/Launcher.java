package controller;

import java.io.IOException;

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
     * Method used to launch application.
     * @param args
     * @throws IOException 
     */
    public static void main(final String[] args) throws IOException {
        BrickBreakerEvo.main(args);
        //LauncherImpl.launchApplication(Main.class, args);
    }
}
