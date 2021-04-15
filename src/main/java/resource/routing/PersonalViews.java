package resource.routing;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import view.GUILayout;

public enum PersonalViews {

    /**
     * Path for load Main Menu Layout.
     */
    SCENE_MAIN_MENU("Layout/MainMenu.fxml", "BrickBreacker-EVO"),

    /**
     * Path for load Character Layout.
     */
    SCENE_CHARACTER_MENU("Layout/CharacterMenu.fxml", "BrickBreacker-EVO"),

    /**
     * Path for load Settings Menu Layout.
     */
    SCENE_SETTINGS("Layout/SettingsMenu.fxml", "Settings"),

    /**
     * Path for load Tutorial Menu Layout.
     */
    SCENE_TUTORIAL("Layout/TutorialMenu.fxml", "TUTORIAL"),

    /**
     * Path for load Ranking Menu Layout.
     */
    SCENE_RANKING("Layout/RankingMenu.fxml", "RANKING"),

    /**
     * Path for load Difficulty Menu Layout.
     */
    SCENE_DIFFICULTY("Layout/DifficultyMenu.fxml", "Difficulty"),

    /**
     * Path for load Game Layout.
     */
    SCENE_GAME("Layout/Game.fxml", "GAME"),

    /**
     * Path for load Creative mode.
     */
    SCENE_CREATIVEMODE("Layout/CreativeMode.fxml", "CREATIVEMODE"),

    /**
     * Path for load Editor mode.
     */
    SCENE_EDITOR_MODE("Layout/MapEditor.fxml", "EDITORMODE"),

    /**
     * Path for load Next Level banner.
     */
    SCENE_NEXT_LEVEL("Layout/NextLevel.fxml", "NEXT_LEVEL"), 

    /**
     * Path for load Game over banner.
     */
    SCENE_GAME_OVER("Layout/ ", "GAMEOVER");

    private String path;
    private String titleScene;
    private transient GUILayout guiLayout = null;

    PersonalViews(final String path, final String titleScene) {
        this.path = path;
        this.titleScene = titleScene;
    }

    public String getTitleScene() {
        return this.titleScene;
    }

    public URL getURL() {
        return ClassLoader.getSystemResource(this.path);
    }

    public InputStream getResourceAsStream() {
        return ClassLoader.getSystemResourceAsStream(this.path);
    }

    public GUILayout loadScene() {
        final FXMLLoader loader = new FXMLLoader(this.getURL());
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        guiLayout = loader.getController();
        return guiLayout;
    }
}
