package view.utilities;

public enum PersonalStyle {

    DEFAULT_STYLE("Style/Style.css");

    private String stylePath;

    PersonalStyle(final String stylePath) {
        this.stylePath = stylePath;
    }

    public String getStylePath() {
        return stylePath;
    }
}
