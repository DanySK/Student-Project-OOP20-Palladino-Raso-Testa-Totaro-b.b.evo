
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
import model.utilities.Boundaries;
import model.utilities.GameObjStatus;
import model.utilities.ScoreAttribute;
import resource.routing.PersonalSounds;
import model.utilities.GameUtilities;

public class EventHandler {

    private final List<Event> eventList = new LinkedList<>();
    private final GameState state;
    private int ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE; //da importare dai settings e viene modificato dai powerup
    private LifeOperationStrategy lifeOperation;

    public EventHandler(final GameState state) {
        this.state = state;
    } 

    /**
     * It acts in different ways based on the type of event passed to it as input.
     */
    public void manageEvent() {

        this.eventList.stream().forEach(event -> {
            final HitEvent hit = (HitEvent) event;
            if (hit.getGameObj().get() instanceof Brick && hit.getGameObj().get().getStatus() == GameObjStatus.DESTRUCTIBLE) {
                final Brick brick = (Brick) hit.getGameObj().get();
                brick.decreaseDurability(ballDamage);
                state.addPoint(ScoreAttribute.BRICK_DAMAGED.getValue());            //add the score of the brick hit
                if (checkDurability(brick)) {
                    state.addPoint(ScoreAttribute.BRICK_BREAK.getValue());          //add the score of the broken brick
                    state.getBoard().removeBrick(brick);
                }
                SoundController.playMusic(PersonalSounds.SOUND_BRICK.getURL().getPath());    //throw the sound for hitting the brick
            } else if (hit.getGameObj().get() instanceof PowerUp && hit.getGameObj().get().getStatus() == GameObjStatus.DESTRUCTIBLE) {
                final PowerUp pwup = (PowerUp) hit.getGameObj().get();
                pwup.decreaseDurability(ballDamage);
                if (checkDurability(pwup)) {
                    pwup.setStatus(GameObjStatus.DROP_POWERUP);
                    pwup.dropPowerUp();
                }
            } 
            else if (hit.getGameObj().get() instanceof PowerUp && hit.getGameObj().get().getStatus() == GameObjStatus.DROP_POWERUP) {
                final PowerUp pwup = (PowerUp) hit.getGameObj().get();
                activatePowerUp(pwup);
            } else if (hit.getGameObj().get() instanceof Paddle) {
                SoundController.playSound(PersonalSounds.SOUND_PADDLE.getURL().getPath());    //throw sound for hitting the paddle
            } else if (hit.getBounds().isPresent()) {
                if (hit.getBounds().get().equals(Boundaries.LOWER)) {
                    this.state.getBoard().removeBall((Ball) hit.getGameObj().get());
                    if (this.state.getBoard().getBalls().isEmpty()) {
                        //this.state.decLives();
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
        case DAMAGE_DOWN, DAMAGE_UP:
            this.ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            pwup.waitSeconds(pwup.getActiveTime());
            this.ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE;
            break;
        case LIFE_DOWN, LIFE_UP:
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
            break;
        case SPEED_UP, SPEED_DOWN:
            break;
        default:
            break;
        }
        //attiva il powerup
        //aspetta X secondi 
        //disattiva il powerUp
        
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
