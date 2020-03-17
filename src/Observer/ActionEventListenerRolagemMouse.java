package Observer;

import Control.Controlador;
import View.InterfacePrincipal;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ActionEventListenerRolagemMouse implements MouseWheelListener {

    InterfacePrincipal padrao;

    public ActionEventListenerRolagemMouse(InterfacePrincipal novaInterface) {
        this.padrao = novaInterface;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        Controlador.defineZoom(padrao, mwe.getWheelRotation(), mwe.isControlDown());
    }
}
