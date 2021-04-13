package controller.event;

import java.util.LinkedList;
import java.util.List;

import controller.game.GamePhase;
import controller.game.GameState;
import controller.sound.SoundController;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.leaderboard.LifeOperationStrategy;
import model.leaderboard.ScoreOperationStrategy;
import model.utilities.Boundaries;
import model.utilities.GameObjStatus;
import model.utilities.ScoreAttribute;
import resource.routing.PersonalSounds;
import model.utilities.GameUtilities;

public class EventHandler {

    private final List<Event> eventList = new LinkedList<>();
    private final GameState state;
    private int ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE;
    private LifeOperationStrategy lifeOperation;
    private ScoreOperationStrategy scoreOperation;

    public EventHandler(final GameState state) {
        this.state = state;
    } 

    /**
     * It acts in different ways based on the type of event passed to it as input.
     */
    public void manageEvent() {

        this.eventList.stream().forEach(event -> {
            final HitEvent hit = (HitEvent) event;
            if (hit.getGameObj().get() instanceof Brick && hit.getGameObj().get().getStatus().equals(GameObjStatus.DESTRUCTIBLE)) {
                final Brick brick = (Brick) hit.getGameObj().get();
                brick.decreaseDurability(ballDamage);
                addPoints(ScoreAttribute.BRICK_DAMAGED.getValue());            //add the score of the brick hit
                if (checkDurability(brick)) {
                    addPoints(ScoreAttribute.BRICK_BREAK.getValue());          //add the score of the broken brick
                    this.state.getBoard().removeBrick(brick);
                    if (brick.getStatus().equals(GameObjStatus.DROP_POWERUP)) { 
                        this.state.getBoard().addPowerUp(brick.dropPowerUp());
                    }
                }
                SoundController.playMusic(PersonalSounds.SOUND_BRICK.getURL().getPath());    //throw the sound for hitting the brick
            } else if (hit.getGameObj().get() instanceof PowerUp) {
                if (hit.getBounds().isPresent()) {
                    this.state.getBoard().removePowerUp((PowerUp) hit.getGameObj().get());
                } else {
                    final PowerUp pwup = (PowerUp) hit.getGameObj().get();
                    activatePowerUp(pwup);
                    this.state.getBoard().removePowerUp(pwup);
                }

            } else if (hit.getGameObj().get() instanceof Paddle) {

                SoundController.playSound(PersonalSounds.SOUND_PADDLE.getURL().getPath());    //throw sound for hitting the paddle

            } else if (hit.getBounds().isPresent()) {
                if (hit.getBounds().get().equals(Boundaries.LOWER)) {
                    this.state.getBoard().removeBall((Ball) hit.getGameObj().get());
                    if (this.state.getBoard().getBalls().isEmpty()) {
                        this.state.getPlayer().lifeOperation(lifeOperation, -1);
                        addPoints(ScoreAttribute.LOST_LIFE.getValue());
                        this.state.setPhase(GamePhase.START);
                    }
                }
                SoundController.playSound(PersonalSounds.SOUND_WALL.getURL().getPath());    //throw sound for hitting the wall
            }
        });
        checkGameState();
        this.eventList.clear();
    }

    private void activatePowerUp(final PowerUp pwup) {
        switch (pwup.getPowerUpType()) {
        case DAMAGE_DOWN:
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            pwup.waitSeconds(pwup.getActiveTime());
            this.ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE;
            break;
        case DAMAGE_UP:
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            pwup.waitSeconds(pwup.getActiveTime());
            this.ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE;
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
     * @param value
     */
    private void addPoints(final int value) {
        this.state.getPlayer().scoreOperation(scoreOperation, value);
    }


    /**
     * 
     * @param e event to add to list of event
     */
    public void addEvent(final Event e) {
        this.eventList.add(e);
    }

    private Boolean checkDurability(final Brick obj) {
        if (obj.getDurability() <= 0) {
            return true;
        } 
        return false;
    }

    private void checkGameState() {
        if (state.getLives() == 0) {
            state.setPhase(GamePhase.LOST);
        } else if (state.getBoard().getBricks().stream()
                                                .filter(i -> i.getStatus().equals(GameObjStatus.DESTRUCTIBLE))
                                                .count() == 0) {
            state.setPhase(GamePhase.WIN);
        }
    }
}
