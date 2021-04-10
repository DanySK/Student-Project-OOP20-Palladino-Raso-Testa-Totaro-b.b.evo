package resource.routing;

public enum PersonalStyle {

    /**
     * Use to set default application component style.
     */
    DEFAULT_STYLE("Style/Style.css");

    private String stylePath;

    PersonalStyle(final String stylePath) {
        this.stylePath = stylePath;
    }

    public String getStylePath() {
        return stylePath;
    }
}
