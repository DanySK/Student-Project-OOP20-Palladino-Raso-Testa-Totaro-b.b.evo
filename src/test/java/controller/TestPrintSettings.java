package controller;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import controller.utilities.IOSettings;
import model.settings.GameSettingsBuilderImpl;
import model.settings.Settings;

class TestPrintSettings {

    private Settings gameSettings;

    @BeforeEach
    void initSettings() {
        this.gameSettings = new GameSettingsBuilderImpl().defaultSettings().build();
    }

    @Test
    void testPrintSettings() {
        assertDoesNotThrow(() -> {
            IOSettings.printInJsonFormat(gameSettings);
        });
    }

    @Test
    void testReadSettings() {
        assertDoesNotThrow(() -> {
            final var readSettings = IOSettings.readSettings();
            assertEquals(this.gameSettings, readSettings);
        });

    }

}
