package view.utilities;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PersonalSounds {

    /***
     * Sound generate by click button event.
     * */
    TICK_BUTTON("Sounds/soundFx/SoundClick.wav", "TickButton"),

    /**
     * The sound is generated by the event of the ball hitting the brick.
     */
    SOUND_BRICK("Sounds/soundFx/Tic-Brick.wav", "SoundBrick"),

    /**
     * The sound is generated by the event of the ball hitting the paddle.
     */
    SOUND_PADDLE("Sounds/soundFx/Tic-Paddle.wav", "SoundPaddle"),

    /**
     * The sound is generated by the event of the ball hitting the wall.
     */
    SOUND_WALL("Sounds/soundFx/Tic-Wall.wav", "SoundWall"),

    /***
     * Sound generate by click event of CheckBox and Radio Button.
     * */
    TICK_SPECIALBUTTON("Sounds/soundFx/SoundSpecialButton.wav", "TickSpecialButton"),

    /***
     * Main Menu Theme.
     * */
    TETRIS_THEME("Sounds/music/KorbenikiAlexTesta(GameVersion).wav", "MainMenuTheme");


    private String path;
    private String name;

    PersonalSounds(final String path, final String name) {
        this.path = path;
        this.name = name;
    }

    public URL getURL() {
        return ClassLoader.getSystemResource(this.path);
    }

    public InputStream getResourceAsStream() {
        return ClassLoader.getSystemResourceAsStream(this.path);
    }

    /**
     * @return the name of the sound
     */
    public String getName() {
        return name;
    }

    /**
     * List to preserve the order in which the sounds are displayed.
     * @return the list of song names returns.
     */
    public static List<String> getSoundsNames() {
        return Arrays.asList(PersonalSounds.values()).stream()
                                            .map(i -> i.getName())
                                            .collect(Collectors.toList());
    }

    /**
     * 
     * @param name to search
     * @return the corresponding enumeration linked to the music.
     */
    public static PersonalSounds getSoundsByName(final String name) {
        return Arrays.asList(PersonalSounds.values()).stream()
                                            .filter(i -> i.getName().equals(name))
                                            .findFirst()
                                            .get();
    }
}
