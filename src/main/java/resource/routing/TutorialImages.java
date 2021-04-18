package resource.routing;

import java.net.URL;

public enum TutorialImages {
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
    TUTORIAL_SETTINGS("Images/video/prova.gif");

    private String path;

    TutorialImages(final String path) {
        this.path = path;
    }

    public URL getURL() {
        return ClassLoader.getSystemResource(this.path);
    }
}
