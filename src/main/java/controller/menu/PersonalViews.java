package controller.menu;

public enum PersonalViews {

    SCENE_MAIN_MENU("/Layout/MainMenu.fxml","BrickBreacker-EVO"),
    SCENE_SETTINGS("/Layout/SettingsMenu.fxml","Settings"),
    SCENE_TUTORIAL("/Layout/TutorialMenu.fxml","TUTORIAL"),
    SCENE_RANKING("/Layout/RankingMenu.fxml","RANKING");

    private String path;
    private String titleScene;

    PersonalViews(final String path, final String titleScene) {
        this.path = path;
        this.titleScene = titleScene;
    }

    public String getPath() {
        return this.path;
    }

    public String getTitleScene() {
        return this.titleScene;
    }
}
