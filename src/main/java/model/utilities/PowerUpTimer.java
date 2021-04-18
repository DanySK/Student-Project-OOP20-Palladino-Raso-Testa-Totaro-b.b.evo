package model.utilities;

import java.util.Timer;
import java.util.TimerTask;

import model.entities.PowerUp;

/**
 * Simple timer class that uses java.util.Timer to schedule a timer task 
 * to execute once an arbitrary amount of seconds have passed.
 */
public class PowerUpTimer {

    private final Timer timer;
    private final PowerUp pwup;

    public PowerUpTimer(final long seconds, final PowerUp pwup) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
            this.pwup = pwup;
        }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            pwup.setIsActive(false);
            System.out.println("powerup disattivato");
            timer.cancel(); //Terminate the timer thread
        }
    }
}
