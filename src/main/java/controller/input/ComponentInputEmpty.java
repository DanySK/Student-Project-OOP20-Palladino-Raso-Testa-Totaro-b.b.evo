package controller.input;

import java.io.Serializable;

import model.entities.GameObject;

public class ComponentInputImpl implements ComponentInput, Serializable {

    private static final long serialVersionUID = 912897323541408067L;

    @Override
    public void update(final GameObject obj, final ControllerInput controlInput) {
      //this component does nothing
    }

}
