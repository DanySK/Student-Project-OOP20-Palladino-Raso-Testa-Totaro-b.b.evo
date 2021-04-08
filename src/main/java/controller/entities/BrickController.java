package controller.entities;

import model.utilities.BrickStatus;

public interface BrickController extends GameObjController{

    int getDurability();

    void decreaseDurability();

    BrickStatus getStatus();
}
