package view;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import controller.BrickBreakerEvo;
import controller.sound.SoundController;




class TestMainMenu extends ApplicationTest {

    private static final String ID_ANCHORPANE = "#window";
    private static final String ID_BORDERPANE = "#panel";
    private static final String ID_BUTTON_PLAY = "#btnPlay";
    private static final String ID_BUTTON_SETTINGS = "#btnSettings";
    private static final String ID_BUTTON_TUTORIAL = "#btnTutorial";
    private static final String ID_BUTTON_RANKING = "#btnRanking";
    private static final int SLEEP_TIME = 2000;

    @BeforeEach
    void launch() throws Exception {
        ApplicationTest.launch(BrickBreakerEvo.class, "");
        sleep(SLEEP_TIME);
    }

    @Test
    void testComponent() {
        //Window
        FxAssert.verifyThat(ID_ANCHORPANE, 
                            NodeMatchers.isNotNull());
        FxAssert.verifyThat(ID_ANCHORPANE,
                            NodeMatchers.isVisible());
        FxAssert.verifyThat(ID_ANCHORPANE,
                            NodeMatchers.isEnabled());

        //Panel
        FxAssert.verifyThat(ID_BORDERPANE, 
                            NodeMatchers.isNotNull());
        FxAssert.verifyThat(ID_BORDERPANE,
                            NodeMatchers.isVisible());
        FxAssert.verifyThat(ID_BORDERPANE,
                            NodeMatchers.isEnabled());
        //Button Play
        FxAssert.verifyThat(ID_BUTTON_PLAY, 
                            NodeMatchers.isNotNull());
        FxAssert.verifyThat(ID_BUTTON_PLAY,
                            LabeledMatchers.hasText("Play"));
        FxAssert.verifyThat(ID_BUTTON_PLAY,
                            NodeMatchers.isVisible());
        FxAssert.verifyThat(ID_BUTTON_PLAY,
                            NodeMatchers.isEnabled());
        //Button Settings
        FxAssert.verifyThat(ID_BUTTON_SETTINGS, 
                            NodeMatchers.isNotNull());
        FxAssert.verifyThat(ID_BUTTON_SETTINGS,
                            LabeledMatchers.hasText("Settings"));
        FxAssert.verifyThat(ID_BUTTON_SETTINGS,
                            NodeMatchers.isVisible());
        FxAssert.verifyThat(ID_BUTTON_SETTINGS,
                            NodeMatchers.isEnabled());

        //Button Tutorial
        FxAssert.verifyThat(ID_BUTTON_TUTORIAL, 
                            NodeMatchers.isNotNull());
        FxAssert.verifyThat(ID_BUTTON_TUTORIAL,
                            LabeledMatchers.hasText("Tutorial"));
        FxAssert.verifyThat(ID_BUTTON_TUTORIAL,
                            NodeMatchers.isVisible());
        FxAssert.verifyThat(ID_BUTTON_TUTORIAL,
                            NodeMatchers.isEnabled());

        //Button Ranking
        FxAssert.verifyThat(ID_BUTTON_RANKING, 
                            NodeMatchers.isNotNull());
        FxAssert.verifyThat(ID_BUTTON_RANKING,
                            LabeledMatchers.hasText("Ranking"));
        FxAssert.verifyThat(ID_BUTTON_RANKING,
                            NodeMatchers.isVisible());
        FxAssert.verifyThat(ID_BUTTON_RANKING,
                            NodeMatchers.isEnabled());
    }

    /*
    @Test
    void testResizeWindow() {

    }
    */

    @Test
    void testNavigableView() {
        assertDoesNotThrow(() -> {
            clickOn("#btnPlay");
            sleep(1000);
            clickOn("#btnBack");
            sleep(1000);
            clickOn("#btnSettings");
            sleep(1000);
            clickOn("#btnBack");
            sleep(1000);
            clickOn("#btnTutorial");
            sleep(1000);
            clickOn("#buttonBack");
            sleep(1000);
            clickOn("#btnRanking");
            sleep(1000);
            clickOn("#buttonBack");
        });
    }

    /*
    @Test
    void testClickOnSettingsButton() {
        assertDoesNotThrow(() -> {
            clickOn("#btnSettings");
        });
    }

    @Test
    void testClickOnTutorialButton() {
        assertDoesNotThrow(() -> {
            clickOn("#btnTutorial");
            sleep(1000);
        });
    }

    @Test
    void testClickRankingButton() {
        assertDoesNotThrow(() -> {
            clickOn("#btnRanking");
        });
    }
    */


}
