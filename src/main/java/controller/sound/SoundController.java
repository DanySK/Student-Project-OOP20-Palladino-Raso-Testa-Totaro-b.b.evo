package controller.sound;



import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public final class SoundController {

    private static Clip clip;

    private SoundController() {

    }

    public static void playSoundFx(final String path) {
        try {
            final Media media = new Media(new File(new URI(path).getPath()).toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(final String path) {
            try {
                final File musicPath = new File(path);
                final var audio = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }

    }

    public static void stopMusic() {
        System.out.println(clip.isActive());
        clip.stop();
    }
}
