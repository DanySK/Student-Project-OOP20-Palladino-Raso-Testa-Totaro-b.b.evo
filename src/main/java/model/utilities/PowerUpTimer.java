package model.utilities;

import java.util.Timer;
import java.util.TimerTask;

import controller.event.PowerUpController;
import model.entities.PowerUp;

/**
 * Simple timer class that uses java.util.Timer to schedule a timer task 
 * to execute once an arbitrary amount of seconds have passed.
 */
public class PowerUpTimer {

    private final Timer timer;
    private final PowerUpController pwupController;

    public PowerUpTimer(final long seconds, final PowerUpController pwupController) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
            this.pwupController = pwupController;
        }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            pwupController.setIsActive(false);
            System.out.println("powerup disattivato");
            timer.cancel(); //Terminate the timer thread
        }
    }
}
