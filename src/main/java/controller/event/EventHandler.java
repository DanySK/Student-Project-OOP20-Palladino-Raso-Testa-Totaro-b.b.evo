package controller.event;

import java.util.LinkedList;
import java.util.Queue;

import controller.game.GamePhase;
import controller.game.GameState;
import controller.sound.SoundController;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.leaderboard.BasicLifeOperationStrategy;
import model.leaderboard.BasicScoreOperationStrategy;
import model.leaderboard.LifeOperationStrategy;
import model.leaderboard.ScoreOperationStrategy;
import model.utilities.Boundaries;
import model.utilities.BrickStatus;
import model.utilities.ScoreAttribute;
import resource.routing.PersonalSounds;
import model.utilities.PowerUpUtilities;

public class EventHandler {

    private final Queue<Event> eventList = new LinkedList<>();
    private final GameState state;
    private int ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE;
    private final LifeOperationStrategy lifeOperation;
    private final ScoreOperationStrategy scoreOperation;

    public EventHandler(final GameState state) {
        this.state = state;
        this.lifeOperation = new BasicLifeOperationStrategy();
        this.scoreOperation = new BasicScoreOperationStrategy();
    } 

    /**
     * It acts in different ways based on the type of event passed to it as input.
     */
    public void manageEvent() {

        this.eventList.stream().forEach(event -> {
            final HitEvent hit = (HitEvent) event;
            if (hit.getGameObj().get() instanceof Brick) {
                final Brick brick = (Brick) hit.getGameObj().get();
                if (brick.getStatus().equals(BrickStatus.DESTRUCTIBLE) || brick.getStatus().equals(BrickStatus.DROP_POWERUP)) {
                    brick.decreaseDurability(ballDamage);
                    addPoints(ScoreAttribute.BRICK_DAMAGED.getValue());                 //add the score of the brick hit
                    if (checkDurability(brick)) {
                        addPoints(ScoreAttribute.BRICK_BREAK.getValue());               //add the score of the broken brick
                        this.state.getBoard().removeBrick(brick);                       //remove brick
                        if (brick.getStatus().equals(BrickStatus.DROP_POWERUP)) { 
                            this.state.getBoard().addPowerUp(brick.dropPowerUp());      //if that brick has a powerUp then it generates it
                        }
                    }
                }
                SoundController.playSoundFx(PersonalSounds.SOUND_BRICK.getURL().getPath());    //throw the sound for hitting the brick
            } else if (hit.getGameObj().get() instanceof PowerUp) {
                if (hit.getBounds().isPresent()) {
                    this.state.getBoard().removePowerUp((PowerUp) hit.getGameObj().get());
                } else {
                    final PowerUp pwup = (PowerUp) hit.getGameObj().get();
                    activatePowerUp(pwup);
                    this.state.getBoard().removePowerUp(pwup);
                }

            } else if (hit.getGameObj().get() instanceof Paddle) {

                SoundController.playSoundFx(PersonalSounds.SOUND_PADDLE.getURL().getPath());    //throw sound for hitting the paddle

            } else if (hit.getBounds().isPresent()) {
                if (hit.getBounds().get().equals(Boundaries.LOWER)) {
                    this.state.getBoard().removeBall((Ball) hit.getGameObj().get());
                    if (this.state.getBoard().getBalls().isEmpty()) {
                        this.state.getPlayer().lifeOperation(lifeOperation, -1);
                        addPoints(ScoreAttribute.LOST_LIFE.getValue());
                        this.state.setPhase(GamePhase.START);
                    }
                }
                SoundController.playSoundFx(PersonalSounds.SOUND_WALL.getURL().getPath());    //throw sound for hitting the wall
            }
        });
        checkGameState();
        this.eventList.clear();
    }

    /**
     * activates the {@link PowerUp}.
     * @param pwup powerup that needs to be activated.
     */
    private void activatePowerUp(final PowerUp pwup) {
        switch (pwup.getPowerUpType()) {
        case DAMAGE_DOWN:
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            pwup.waitSeconds(pwup.getActiveTime());
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE;
            break;
        case DAMAGE_UP:
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            pwup.waitSeconds(pwup.getActiveTime());
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE;
            break;
        case LIFE_DOWN:
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
            break;
        case LIFE_UP:
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
            break;
        case SPEED_UP:
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() + pwup.getSpeedModifier()));
            pwup.waitSeconds(pwup.getActiveTime());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() - pwup.getSpeedModifier()));
            break;
        case SPEED_DOWN:
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() + pwup.getSpeedModifier()));
            pwup.waitSeconds(pwup.getActiveTime());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() - pwup.getSpeedModifier()));
            break;
        default:
            break;
        }
    }

    /**
     * adds points to the player's score.
     * @param value value to be added to the player's score
     */
    private void addPoints(final int value) {
        this.state.getPlayer().scoreOperation(scoreOperation, value);
    }


    /**
     * adds a new event to the event list.
     * @param e event to add to list of event
     */
    public void addEvent(final Event e) {
        this.eventList.add(e);
    }

    /**
     * checks {@link Brick} durability.
     * @param brick brick to be checked
     * @return true if the durability is <= 0, false otherwise.
     */
    private Boolean checkDurability(final Brick brick) {
        if (brick.getDurability() <= 0) {
            return true;
        } 
        return false;
    }

    /**
     * set the game phase in Win if there are no more bricks or 
     * in Lost if the player has no more lives.
     */
    private void checkGameState() {
        if (state.getLives() == 0) {
            state.setPhase(GamePhase.LOST);
        } else if (state.getBoard().getBricks().stream()
                                                .filter(i -> i.getStatus().equals(BrickStatus.DESTRUCTIBLE) || i.getStatus().equals(BrickStatus.DROP_POWERUP))
                                                .count() == 0) {
            state.setPhase(GamePhase.WIN);
        }
    }
}
