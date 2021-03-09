package controller.menu;

public enum PersonalFonts {
    /**
     * Font of all button and label. 
     */
    FONT_BUTTON("/res/Fonts/Staatliches-Regular.ttf"),

    /**
     * Font of all title.
     */
    FONT_TITLE("/res/Fonts/BungeeShade-Regular.ttf");

    private String path;

    PersonalFonts(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

}
