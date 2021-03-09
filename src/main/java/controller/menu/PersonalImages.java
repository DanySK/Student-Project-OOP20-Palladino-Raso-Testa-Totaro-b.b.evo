package controller.menu;

public enum PersonalImages {
    PLAY_IMG("/res/Images/MainMenuImg/BtnPlay.png"),
    SETTINGS_IMG("/res/Images/MainMenuImg/Settings.png"),
    TUTORIAL_IMG("/res/Images/MainMenuImg/Credit-card.png"),
    RANKING_IMG("/res/Images/MainMenuImg/Video.png");

    private String path;

    PersonalImages(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
