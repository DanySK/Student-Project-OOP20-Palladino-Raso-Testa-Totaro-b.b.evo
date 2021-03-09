package controller.menu;

public enum PersonalViews {

    SCENE_MAIN_MENU("/layout/MainMenu.fxml","BrickBreacker-EVO"),
    SCENE_SETTINGS("/layout/SettingsMenu.fxml","Settings"),
    SCENE_TUTORIAL("/layout/TutorialMenu.fxml","TUTORIAL"),
    SCENE_RANKING("/layout/RankingMenu.fxml","RANKING");

    private String path;
    private String titleScene;

    PersonalViews(String path,String titleScene) {
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
