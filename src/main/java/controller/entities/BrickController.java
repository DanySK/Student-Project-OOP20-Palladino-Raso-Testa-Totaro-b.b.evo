package controller.entities;

import model.utilities.GameObjStatus;

public interface BrickController extends GameObjController{

    int getDurability();

    void decreaseDurability();

    GameObjStatus getStatus();
}
