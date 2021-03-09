package controller.menu;

public enum PersonalFonts {

    /*
     * Font of all button and label 
     * */
    FONT_BUTTON("/fonts/Staatliches-Regular.ttf"),

    /*
     * Font of all title
     * */
    FONT_TITLE("/fonts/BungeeShade-Regular.ttf");

    private String path;

    PersonalFonts(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

}
