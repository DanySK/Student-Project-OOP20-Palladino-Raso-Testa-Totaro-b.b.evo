package view.utilities;

public enum PersonalFonts {
    /**
     * Font of all button and label. 
     */
    FONT_BUTTON("Fonts/Staatliches-Regular.ttf"),

    /**
     * Font of all title.
     */
    FONT_TITLE("Fonts/BungeeShade-Regular.ttf");

    private String path;

    PersonalFonts(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

}
