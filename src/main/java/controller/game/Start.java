package controller.game;

import model.utilities.Position;

public enum Start {

    /**
     * the default states.
     */
    PLAYER_ONE(new Position(290, 580), 78, 20),

    /**
     * the default states.
     */
    BALL(new Position(330, 570), 10, 10);

    private final Position spawnPoint;
    private final int initWidth;
    private final int initHeight;

    Start(final Position spawnPoint, final int initWidth, final int initHeight) {
        this.spawnPoint = spawnPoint;
        this.initHeight = initHeight;
        this.initWidth = initWidth;
    }

    public Position getSpawnPoint() {
        return spawnPoint;
    }

    public int getInitWidth() {
        return initWidth;
    }

    public int getInitHeight() {
        return initHeight;
    }

}
