package controller;
import com.sun.javafx.application.LauncherImpl;

public final class Launcher {

    private Launcher() {

    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        LauncherImpl.launchApplication(Main.class, args);
    }
}
