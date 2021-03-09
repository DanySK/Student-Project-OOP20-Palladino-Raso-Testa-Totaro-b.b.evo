package controller.menu;

public enum PersonalViews {

    SCENE_MAIN_MENU("res/Layout/MainMenu.fxml","BrickBreacker-EVO"),
    SCENE_SETTINGS("res/Layout/SettingsMenu.fxml","Settings"),
    SCENE_TUTORIAL("res/Layout/TutorialMenu.fxml","TUTORIAL"),
    SCENE_RANKING("res/Layout/RankingMenu.fxml","RANKING");

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
