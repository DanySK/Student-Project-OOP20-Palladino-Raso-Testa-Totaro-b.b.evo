package model.entities;

import javafx.scene.shape.Rectangle;
import model.utilities.GameObjectType;
import model.utilities.Position;
import model.utilities.PowerUpType;

public class PoweUpImpl extends BrickImpl implements PowerUp{
    private PowerUpType pwtype;
    
    public PoweUpImpl(int width, int height, Position position, GameObjectType type, int durability) {
        super(width, height, position, type, durability);
    }
    
    @Override
    public Boolean isBroken() {
        if (this.durability <= 0) {
            dropPowerUp();
            return true;
        }
        return false;
    }
    
    @Override
    public void dropPowerUp() {
        //richiama la view per cambiare la grafica del brick(?)
        
        //inizia a cadere verso il basso
        
        //se tocca il paddle attiva il powerup e distrugge il gameobj 
        
        //se tocca il fondo non attiva il pwrup e distrugge il gameobj
    
    }

    @Override
    public void activatePowerUp(int seconds) {
        //attiva il powerup
        
    }


}
