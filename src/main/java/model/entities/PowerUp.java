package model.entities;

public interface PowerUp extends Brick {
    /*drops the powerup if Brick.isBroken()=true */
    void dropPowerUp();
    
    /*activates the powerup when its boundaries hit the paddle*/
    void activatePowerUp(int seconds);
    
}
