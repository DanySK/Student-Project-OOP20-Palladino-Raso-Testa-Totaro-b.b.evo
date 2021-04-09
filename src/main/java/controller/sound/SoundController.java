package controller.sound;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.utilities.IOSettings;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.utilities.GameUtilities;

public final class SoundController {

    private static Clip clip;
    private static boolean clipIsActived = false;
    private static boolean canPermiseMusic = IOSettings.readSettings(GameUtilities.SETTINGS_PATH).isEnableMusic();
    private static boolean canPermiseFX = IOSettings.readSettings(GameUtilities.SETTINGS_PATH).isEnableSoundFx();


    private SoundController() {

    }

    public static void playSoundFx(final String path) {
        try {
            if (canPermiseFX) {
                final Media media = new Media(new File(new URI(path).getPath()).toURI().toString());
                final MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void stopFx() {
        canPermiseFX = false;
    }

    public static void enableSoundFx() {
        canPermiseFX = true;
    }

    public static void playMusic(final String path) {
            try {
                    if (!clipIsActived && canPermiseMusic)  {
                        final File musicPath = new File(path);
                        final var audio = AudioSystem.getAudioInputStream(musicPath);
                        clip = AudioSystem.getClip();
                        clip.open(audio);
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        clipIsActived = true;
                    }


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
    }

    public static void playSound(final String path) {
        try {
            if (!clipIsActived && canPermiseMusic)  {
                final File musicPath = new File(path);
                final var audio = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clipIsActived = true;
            }
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }
}

    public static void stopMusic() {
        if (clip.isActive()) {
            clip.stop();
        }
        clipIsActived = false;
    }

    public static void enableMusic() {
        canPermiseMusic = true;
    }

    public static void disableMusic() {
        canPermiseMusic = false;
    }

}
