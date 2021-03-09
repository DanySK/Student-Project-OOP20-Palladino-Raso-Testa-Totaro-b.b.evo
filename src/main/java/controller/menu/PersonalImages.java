package controller.menu;

public enum PersonalImages {
    PLAY_IMG("/Images/MainMenuImg/BtnPlay.png"),
    SETTINGS_IMG("/Images/MainMenuImg/Settings.png"),
    TUTORIAL_IMG("/Images/MainMenuImg/Credit-card.png"),
    RANKING_IMG("/Images/MainMenuImg/Video.png");

    private String path;

    PersonalImages(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
