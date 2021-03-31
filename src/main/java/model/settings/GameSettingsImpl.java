package model.settings;

import model.utilities.Difficulty;

public class GameSettingsImpl implements Settings {

    private static final long serialVersionUID = 5553383064816848320L;
    private final boolean isEnableoundFx;
    private final boolean isEnableMusic;
    private final boolean useLeftAndRight;
    private final boolean useUpAndDown;
    private final Difficulty difficulty;

    /**
     * 
     * Create a new GameSettings whit specific parameter.
     * @param isEnableoundFx
     * @param isEnableMusic
     * @param useLeftAndRight
     * @param useUpAndDown
     * @param difficulty
     */
    public GameSettingsImpl(final boolean isEnableoundFx, final boolean isEnableMusic, final boolean useLeftAndRight,
                            final boolean useUpAndDown, final Difficulty difficulty) {
        this.isEnableoundFx = isEnableoundFx;
        this.isEnableMusic = isEnableMusic;
        this.useLeftAndRight = useLeftAndRight;
        this.useUpAndDown = useUpAndDown;
        this.difficulty = difficulty;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isEnableoundFx() {
        return this.isEnableoundFx;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isEnableMusic() {
        return this.isEnableMusic;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean useLeftAndRight() {
        return this.useLeftAndRight;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean useUpAndDown() {
        return this.useUpAndDown;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

}
