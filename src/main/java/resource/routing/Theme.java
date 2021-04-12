package resource.routing;

public enum Theme {
    DEFAULT("Default"),
    ARKANOID("Arkanoid"),
    GALAGA("Galaga"),
    PACMAN("Pacman"),
    DONKEY_KONG("DonkeyKong"),
    CRASH_BANDICOOT("CrashBandicoot"),
    SUPER_MARIO("SuperMario");
    
    private String themeName;

    Theme(final String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return this.themeName;
    }
}
