package controller.event;

import java.util.LinkedList;
import java.util.List;

import controller.game.GamePhase;
import controller.game.GameState;
import model.entities.Brick;
import model.entities.Paddle;
import model.utilities.Boundaries;
import model.utilities.BrickStatus;

public class EventHandler {
    private final List<Event> eventList = new LinkedList<>();

    private final GameState state;

    public EventHandler(final GameState state) {
        this.state = state;
    } 

    /**
     * 
     */
    public void manageEvent() {
        
    }

    /**
     * 
     * @param e
     */
    public void addEvent(final Event e) {
        this.eventList.add(e);
    }

    private void checkGameState() {
        if (state.getLives() == 0) {
            state.setPhase(GamePhase.LOST);
        } else if (state.getBoard().getBricks().stream()
                                                .filter(i -> i.getStatus().equals(BrickStatus.DESTR) )
                                                .count() == 0) {
            //state.addBonus();
            state.setPhase(GamePhase.WIN);
        }
    }
}
