package controller.menu;

public enum PersonalImages {
    PLAY_IMG("/images/mainMenuImg/btnPlay.png"),
    SETTINGS_IMG("/images/mainMenuImg/settings.png"),
    TUTORIAL_IMG("/images/mainMenuImg/credit-card.png"),
    RANKING_IMG("/images/mainMenuImg/video.png");

    private String path;

    PersonalImages(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
