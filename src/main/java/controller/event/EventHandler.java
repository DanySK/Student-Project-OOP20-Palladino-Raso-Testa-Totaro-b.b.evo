package controller.event;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import controller.game.GamePhase;
import controller.game.GameState;
import controller.sound.SoundController;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.utilities.Boundaries;
import model.utilities.GameObjStatus;
import model.utilities.ScoreAttribute;
import view.utilities.PersonalSounds;

public class EventHandler {

    private final List<Event> eventList = new LinkedList<>();
    private final GameState state;
    private SoundController music;
    private int ballDamage = 1; //da importare dai settings e viene modificato dai powerup

    public EventHandler(final GameState state) {
        this.state = state;
    } 

    /**
     * It acts in different ways based on the type of event passed to it as input.
     */
    public void manageEvent() {

        this.eventList.stream().forEach(event -> {
            final HitEvent hit = (HitEvent) event;
            if (hit.getGameObj().get() instanceof Brick && hit.getGameObj().get().getStatus() == GameObjStatus.DESTR) {
                final Brick brick = (Brick) hit.getGameObj().get();
                brick.decreaseDurability(ballDamage);
                state.addPoint(ScoreAttribute.BRICK_DAMAGED.getValue());            //add the score of the brick hit
                if (checkDurability(brick)) {
                    brick.setStatus(GameObjStatus.BROKEN); //non so se ci servira'
                    state.addPoint(ScoreAttribute.BRICK_BREAK.getValue());          //add the score of the broken brick
                    state.getBoard().removeBrick(brick);
                }
                this.music.playMusic(PersonalSounds.SOUND_BRICK.getURL().getPath());    //throw the sound for hitting the brick
            } else if (hit.getGameObj().get() instanceof PowerUp && hit.getGameObj().get().getStatus() == GameObjStatus.DESTR) {
                final PowerUp pwup = (PowerUp) hit.getGameObj().get();
                pwup.decreaseDurability(ballDamage);
                if (checkDurability(pwup)) {
           ///////////////////////////////////////////////////
                }
            } else if (hit.getGameObj().get() instanceof Paddle) {
                this.music.playMusic(PersonalSounds.SOUND_PADDLE.getURL().getPath());    //throw sound for hitting the paddle
            } else if (hit.getBounds().isPresent()) {
                if (hit.getBounds().get().equals(Boundaries.LOWER)) {
                    this.state.getBoard().removeBall((Ball) hit.getGameObj().get());
                    if (this.state.getBoard().getBalls().isEmpty()) {
                        this.state.decLives();
                        this.state.setPhase(GamePhase.INIT);
                    }
                }
                this.music.playMusic(PersonalSounds.SOUND_WALL.getURL().getPath());    //throw sound for hitting the wall
            }
        });
        checkGameState();
        this.eventList.clear();
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
                                                .filter(i -> i.getStatus().equals(GameObjStatus.DESTR) )
                                                .count() == 0) {
            //state.addBonus();
            state.setPhase(GamePhase.WIN);
        }
    }
}
