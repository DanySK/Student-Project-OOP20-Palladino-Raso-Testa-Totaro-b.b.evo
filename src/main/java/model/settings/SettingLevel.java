package model.settings;

import java.io.Serializable;

import model.mapeditor.Level;
import model.mapeditor.LevelSelection;


public final class SettingLevel implements Serializable {

    private static final long serialVersionUID = -2472026492104740049L;
    private final Level selectedLevel;

    private SettingLevel(final Level selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    /**
     * @return the selectedLevel
     */
    public Level getSelectedLevel() {
        return selectedLevel;
    }

    public static final class SettingLevelBuilder {

        private Level selectedLevel;

        public SettingLevelBuilder() {
            this.selectedLevel = LevelSelection.LEVEL1.getLevel();
        }

        /**
         * set the settings by copying the parameters from other settings.
         * @param settings to copy
         * @return returns himself following the pattern builder
         */
        public SettingLevelBuilder fromSettings(final SettingLevel settings) {
            this.selectedLevel = settings.getSelectedLevel();
            return this;
        }

        public SettingLevelBuilder selectLevel(final Level selectedLevel) {
            this.selectedLevel = selectedLevel;
            return this;
        }

        /**
         * 
         * @return build settings making sure that the values entered are correct
         */
        public SettingLevel build() {
            if (this.selectedLevel == null) {
                throw new IllegalStateException();
            }
            return new SettingLevel(selectedLevel);
        }
    }
}
