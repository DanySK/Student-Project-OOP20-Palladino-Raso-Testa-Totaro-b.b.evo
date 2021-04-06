package model.entities;

import java.util.Optional;

import controller.input.ComponentInput;
import controller.input.ControllerInput;
import controller.physics.ComponentPhysics;
import model.utilities.Position;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;

public abstract class GameObjectImpl implements GameObject {

    private Position pos;
    private DirVector vel;
    private double speed;
    private int height;
    private int width;
    private final Optional<ComponentPhysics> physics;
    private final Optional<ComponentInput> input;
    private final Optional<ComponentGraphics> graphics;

    public GameObjectImpl(final Position pos, final DirVector vel, final double speed, final int height, final int width,
            final ComponentPhysics physics, final ComponentInput input, final ComponentGraphics graphics) {
        this.pos = pos;
        this.vel = vel;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.physics = Optional.of(physics);
        this.input = Optional.of(input);
        this.graphics = Optional.of(graphics);
    }

    public GameObjectImpl(final Position pos, final DirVector vel, final double speed, final int height, final int width,
            final ComponentPhysics physics, final ComponentGraphics graphics) {
        this.pos = pos;
        this.vel = vel;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.physics = Optional.of(physics);
        this.input = Optional.empty();
        this.graphics = Optional.of(graphics);
    }

    public GameObjectImpl(final Position pos, final DirVector vel, final double speed, final int height, final int width) {
        this.pos = pos;
        this.vel = vel;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.physics = Optional.empty();
        this.input = Optional.empty();
        this.graphics = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final Position pos) {
        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirVector getVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVel(final DirVector vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * @return component Physics
     */
    protected Optional<ComponentPhysics> getComponentPhysics() {
        return this.physics;
    }

    /**
     * @return component Input
     */
    protected Optional<ComponentInput> getComponentInput() {
        return this.input;
    }

    /**
     * @return component Graphics
     */
    protected Optional<ComponentGraphics> getComponentGraphics() {
        return this.graphics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updatePhysics(int timeElapsed, GameBoardImpl world);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateInput(ControllerInput controller);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateGraphics(AdapterGraphics graphicsAdapter);

}
