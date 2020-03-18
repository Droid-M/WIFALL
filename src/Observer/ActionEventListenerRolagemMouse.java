package Observer;

import Control.Controlador;
import View.InterfacePrincipal;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ActionEventListenerRolagemMouse implements MouseWheelListener {

    public ActionEventListenerRolagemMouse(Controlador aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ActionEventListenerRolagemMouse(Controlador aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        Controlador.defineZoom(mwe.getWheelRotation(), mwe.isControlDown());
    }
}
