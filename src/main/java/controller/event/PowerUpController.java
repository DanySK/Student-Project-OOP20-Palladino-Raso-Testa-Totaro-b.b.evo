package controller.event;

import controller.game.GameState;
import model.entities.PowerUp;
import model.leaderboard.BasicLifeOperationStrategy;
import model.leaderboard.BasicScoreOperationStrategy;
import model.leaderboard.LifeOperationStrategy;
import model.leaderboard.ScoreOperationStrategy;
import model.utilities.PowerUpTimer;
import model.utilities.PowerUpType;
import model.utilities.PowerUpUtilities;
import model.utilities.ScoreAttribute;

public class PowerUpController {
    private int ballDamage;
    private final PowerUp pwup;
    private Boolean isActive;
    private final GameState state;
    private final LifeOperationStrategy lifeOperation;
    private final ScoreOperationStrategy scoreOperation;

    public PowerUpController(final PowerUp pwup, final GameState state) {
        this.pwup = pwup;
        this.isActive = false;
        this.state = state;
        this.lifeOperation = new BasicLifeOperationStrategy();
        this.scoreOperation = new BasicScoreOperationStrategy();
        this.ballDamage = PowerUpUtilities.BALL_DAMAGE;
    }


    /**
     * activates the {@link PowerUp}.
     * @param pwup {@link PowerUp} that needs to be activated.
     */
    public void activatePowerUp(final PowerUp pwup) {
        this.setIsActive(true);
        System.out.println("powerup attivato: " + pwup.getPowerUpType().name());
        if (pwup.getPowerUpType().equals(PowerUpType.DAMAGE_DOWN)) {
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            waitSeconds(pwup.getActiveTime(), pwup);
        } else if (pwup.getPowerUpType().equals(PowerUpType.DAMAGE_UP)) {
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier();
            waitSeconds(pwup.getActiveTime(), pwup);
        } else if (pwup.getPowerUpType().equals(PowerUpType.LIFE_DOWN)) {
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
        } else if (pwup.getPowerUpType().equals(PowerUpType.LIFE_UP)) {
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
        } else if (pwup.getPowerUpType().equals(PowerUpType.SPEED_DOWN)) {
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() + pwup.getSpeedModifier()));
            waitSeconds(pwup.getActiveTime(), pwup);
        } else if (pwup.getPowerUpType().equals(PowerUpType.SPEED_UP)) {
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() + pwup.getSpeedModifier()));
            waitSeconds(pwup.getActiveTime(), pwup);
        } 
        if (!this.getIsActive()) {
            deactivatePowerUp(pwup);
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
     * deactivates the powerup.
     * @param pwup to be deactivated
     */
    public void deactivatePowerUp(final PowerUp pwup) {
        if (pwup.getPowerUpType().equals(PowerUpType.DAMAGE_DOWN) 
                ||  pwup.getPowerUpType().equals(PowerUpType.DAMAGE_UP)) {
            this.ballDamage = PowerUpUtilities.DEFAULT_BALL_DAMAGE;
        } else if (pwup.getPowerUpType().equals(PowerUpType.SPEED_DOWN) 
                ||  pwup.getPowerUpType().equals(PowerUpType.SPEED_UP)) {
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() - pwup.getSpeedModifier()));
        }
    }

    /**
     * this method is used after powerup activation
     * to waits some seconds.
     * @param seconds amount of seconds to wait
     * @param pwup powerup that needs to wait 
     */
    public void waitSeconds(final long seconds, final PowerUp pwup) {
        System.out.println("aspetto " + pwup.getPowerUpType().getActiveTime() + " secondi");
        new PowerUpTimer(seconds, this);
    }


    /**
     * getter for isActive boolean value.
     * @return true if the powerup has been activated,
     * false otherwise
     */
    public Boolean getIsActive() {
        return this.pwup.isActive;
    }

    /**
     * setter for isActive boolean.
     * @param value the value to set
     */
    public void setIsActive(final Boolean value) {
        this.pwup.isActive = value;
    }


    /**
     * getter for pwup.
     * @return PowerUp powerup
     */
    public PowerUp getPwup() {
        return this.pwup;
    }

}
