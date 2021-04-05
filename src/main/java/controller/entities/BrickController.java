package controller.entities;

import model.utilities.Status;

public interface BrickController extends GameObjController{

    int getDurability();

    void decreaseDurability();

    Status getStatus();
}
