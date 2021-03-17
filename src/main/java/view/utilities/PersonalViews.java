package view.utilities;

public enum PersonalViews {

    /**
     * Path for load Main Menu Layout.
     */
    SCENE_MAIN_MENU("/Layout/MainMenu.fxml", "BrickBreacker-EVO"),

    /**
     * Path for load Character Layout.
     */
    SCENE_CHARACTER_MENU("/Layout/CharacterMenu.fxml", "BrickBreacker-EVO"),

    /**
     * Path for load Settings Menu Layout.
     */
    SCENE_SETTINGS("/Layout/SettingsMenu.fxml", "Settings"),

    /**
     * Path for load Tutorial Menu Layout.
     */
    SCENE_TUTORIAL("/Layout/TutorialMenu.fxml", "TUTORIAL"),

    /**
     * Path for load Ranking Menu Layout.
     */
    SCENE_RANKING("/Layout/RankingMenu.fxml", "RANKING");

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
