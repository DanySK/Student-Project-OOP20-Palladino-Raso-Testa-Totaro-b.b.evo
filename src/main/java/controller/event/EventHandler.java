package controller.event;

import java.util.LinkedList;
import java.util.List;

import controller.game.GamePhase;
import controller.game.GameState;
import model.entities.Brick;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.utilities.GameObjStatus;
import model.utilities.GameUtilities;

public class EventHandler {

    private final List<Event> eventList = new LinkedList<>();
    private final GameState state;
    private int ballDamage = GameUtilities.DEFAULT_BALL_DAMAGE; //da importare dai settings e viene modificato dai powerup

    public EventHandler(final GameState state) {
        this.state = state;
    } 

    /**
     * It acts in different ways based on the type of event passed to it as input.
     */
    public void manageEvent() {

        this.eventList.stream().forEach(event -> {
            final HitEvent hit = (HitEvent) event;
            if (hit.getGameObj() instanceof Brick && hit.getGameObj().getStatus() == GameObjStatus.DESTR) {
                final Brick brick = (Brick) hit.getGameObj();
                    brick.decreaseDurability(ballDamage);
                    //state.addPoint(brick.getPoint);
                    if (checkDurability(brick)) {
                        brick.setStatus(GameObjStatus.BROKEN); //non so se ci servira'
                        state.getBoard().removeBrick(brick);
                    }

            } else if (hit.getGameObj() instanceof PowerUp && hit.getGameObj().getStatus() == GameObjStatus.DESTR) {
                final PowerUp pwup = (PowerUp) hit.getGameObj();
                pwup.decreaseDurability(ballDamage);
                if (checkDurability(pwup)) {
                    
                }
            } else if (hit.getGameObj() instanceof Paddle) {
                
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
